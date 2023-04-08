package com.micro.www.service;

import com.micro.www.api.ISystemInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author XiongJiaMin
 * @apiNote JDK 动态代理测试
 * @since 2022-09-09 10:36
 **/
public class SystemInfoServiceProxy implements InvocationHandler {

    private final static Logger logger = LogManager.getLogger(SystemInfoServiceProxy.class);

    private ISystemInfoService systemInfoService;

    public void setSystemInfoService(ISystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Jdk - Method is start invoke");
        Object invoke = method.invoke(systemInfoService, args);
        logger.info("Jdk - Method is end invoke");
        return invoke;
    }

    /**
     *  创建代理对象
     */
    public Object creatProxy() {
        return Proxy.newProxyInstance(systemInfoService.getClass().getClassLoader(), systemInfoService.getClass().getInterfaces(), this);
    }
}
