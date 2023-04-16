package com.micro.common.web.aop;

import com.micro.common.anno.ApiLogHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * AOP 静态代理
 * @program: www
 * @description: 日志切面拦截
 * <p>
 *     依旧可以处理接口数据，日志等, 后续新增继承BaseAspect再实现即可
 * </p>
 * @author: XiongJiaMin
 * @create: 2021-11-30 10:37
 **/
@Aspect
@Component
public class ApiLogAspect extends BaseAspect {

    private final static Logger logger = LogManager.getLogger(ApiLogAspect.class);

    @Override
    @Pointcut(value = "@annotation(com.micro.common.anno.ApiLogHandler)")
    public void pointCut () {}

    @Override
    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("Aspect - The method {} is start", signature.getMethod().getName());
    }

    @Override
    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("Aspect - The method {} is end", signature.getMethod().getName());
    }

    @Override
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ApiLogHandler apiLogHandler = signature.getMethod().getDeclaredAnnotation(ApiLogHandler.class);
        logger.info("Aspect - The method {} is execute, log catch model is {}", signature.getMethod().getName(), apiLogHandler.model());
        return joinPoint.proceed();
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("Aspect - The method {} is return", signature.getMethod().getName());
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void afterReturning(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("Aspect - The method {} is throw exception", signature.getMethod().getName());
    }
}
