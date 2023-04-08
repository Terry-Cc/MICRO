package com.micro.customer.config;

import com.micro.common.anno.ApiHandleAll;
import com.micro.common.web.interceptor.SimpleApiInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XiongJiaMin
 * @apiNote 拦截器配置
 * @since 2022-09-08 14:39
 **/
@Configuration
public class SimpleApiInterceptorConfig {

    @Bean
    public DefaultPointcutAdvisor SimpleApiInterceptor() {
        //  默认的切点通知器
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        // 设置对注解创建切点
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(ApiHandleAll.class);
        // 设置拦截器
        SimpleApiInterceptor interceptor = new SimpleApiInterceptor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
