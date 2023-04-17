package com.micro.services.provider.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.micro.common.constant.Constants;
import com.micro.common.entity.User;
import com.micro.common.enums.ResultEnum;
import com.micro.common.exception.BusinessException;
import com.micro.common.util.idutils.SnowFlakeIdGenerator;
import com.micro.common.util.other.SystemUtils;
import com.micro.common.web.listener.event.SignEvent;
import com.micro.common.web.vo.CommRequest;
import com.micro.common.web.vo.CommResponse;
import com.micro.services.api.ISystemInfoService;
import com.micro.services.provider.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: micro
 * @description: 系统参数查询
 * @author: XiongJiaMin
 * @create: 2021-12-03 17:58
 **/
@DubboService
@SuppressWarnings("unused")
public class SystemInfoServiceImpl implements ISystemInfoService {

    private final static Logger logger = LogManager.getLogger(SystemInfoServiceImpl.class);

    @Resource
    public SnowFlakeIdGenerator idGenerator;

    @Resource
    public ApplicationContext context;

    @Resource
    public UserMapper userMapper;

    @Resource
    public MybatisProperties properties;

    @Resource
    public DruidDataSource druidDataSource;

    @Override
    public CommResponse getSystemInfo(CommRequest request) throws BusinessException {
        CommResponse response = new CommResponse();
        if (Constants.STR_TWO.equals(request.getUserId())) {
            throw new BusinessException("TEST");
        }
        try {
            List<User> userList = userMapper.listUser(request.getUserName());
            Map<String, Object> data = new HashMap<>();
            data.put("snowId", idGenerator.generateId());
            data.put("systemItem", SystemUtils.getComMsg());
            data.put("mappers", properties.getConfiguration().getMappedStatementNames().toString());
            data.put("datasourceProperties", druidDataSource.toString());
            response.setResult(ResultEnum.COMMON_SUCCESS_RESP).setData(data);
            // 通知监听器
            User user = userMapper.selectById(request.getUserId());
            context.publishEvent(new SignEvent(context, user));
            // 模拟 CgLib 动态代理
            Class<SystemInfoServiceImpl> clazz = SystemInfoServiceImpl.class;
            final SystemInfoServiceImpl service = new SystemInfoServiceImpl();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(clazz);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                logger.info("CgLib - Method is start invoke");
                Object invoke = method.invoke(service, objects);
                logger.info("CgLib - Method is end invoke");
                return invoke;
            });
            SystemInfoServiceImpl proxyCgLib = (SystemInfoServiceImpl) enhancer.create();
            proxyCgLib.testAop();
            // 模拟 JDK 动态代理
            SystemInfoServiceProxy proxy = new SystemInfoServiceProxy();
            proxy.setSystemInfoService(service);
            ISystemInfoService proxyJdk = (ISystemInfoService) proxy.creatProxy();
            proxyJdk.testAop();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return response;
    }

    @Override
    public void testAop() {
        logger.info("Aop - Method is invoking");
    }
}
