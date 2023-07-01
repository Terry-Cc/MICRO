package com.micro.basecase.javamodel.structuraltype.proxypattern.cglibtrandaproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  车站代理点
 * </p>
 * @since 2023/7/1 15:18
 */
public class StationCglibProxy implements MethodInterceptor {

    public TrainStation getProxy(Class<? extends TrainStation> clz) {
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 设置父类Class
        enhancer.setSuperclass(clz);
        // 设置当前对象为回调
        enhancer.setCallback(this);
        return (TrainStation) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("代理点代理费: 100元");
        // 调用父类方法
        return proxy.invokeSuper(obj, args);
    }
}
