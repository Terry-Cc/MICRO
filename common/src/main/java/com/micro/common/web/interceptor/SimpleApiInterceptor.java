package com.micro.common.web.interceptor;

import com.micro.common.anno.ApiHandleAll;
import com.micro.common.util.other.CommonUtils;
import com.micro.common.web.util.WebUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.AnnotatedElement;

/**
 * @author XiongJiaMin
 * @apiNote 接口拦截器
 * <p>
 *     可以处理请求参数，响应，日志捕获等
 * </p>
 * @since 2022-09-08 11:13
 **/
public class SimpleApiInterceptor implements MethodInterceptor {

    private final static Logger logger = LogManager.getLogger(SimpleApiInterceptor.class);
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        AnnotatedElement element = methodInvocation.getThis().getClass();
        ApiHandleAll annotation = element.getAnnotation(ApiHandleAll.class);
        if (!CommonUtils.isEmpty(annotation)) {
            logger.info("开始处理: {}", WebUtil::getUrl);
        }
        return methodInvocation.proceed();
    }
}
