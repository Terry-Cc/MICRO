package com.micro.common.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @program: www
 * @description: 通用切面
 * @author: XiongJiaMin
 * @create: 2021-11-30 15:53
 **/
@SuppressWarnings("unused")
public abstract class BaseAspect {

    /**
     * 切点
     * 控制层所有 路径
     */
    public abstract void pointCut ();

    /**
     * 前置通知
     * @param joinPoint 切点
     */
    public abstract void before (JoinPoint joinPoint);

    /**
     * 后置通知
     * @param joinPoint 切点
     */
    public abstract void after (JoinPoint joinPoint);

    /**
     * 环绕通知
     * @param joinPoint 切点
     * @return 切点执行结果
     * @throws Throwable 切点执行异常
     */
    public abstract Object around(ProceedingJoinPoint joinPoint) throws Throwable;
}
