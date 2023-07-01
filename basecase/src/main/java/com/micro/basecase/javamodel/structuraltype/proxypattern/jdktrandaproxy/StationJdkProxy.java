package com.micro.basecase.javamodel.structuraltype.proxypattern.jdktrandaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  售票代理点
 * </p>
 * @since 2023/7/1 14:59
 */
public class StationJdkProxy implements InvocationHandler {

    private TrainStation trainStation;

    public TrainStation getProxy(TrainStation trainStation) {
        this.trainStation = trainStation;
        Class<? extends TrainStation> stationClass = trainStation.getClass();
        return (TrainStation) Proxy.newProxyInstance(stationClass.getClassLoader(), stationClass.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理点代理费: 10 元");
        return method.invoke(this.trainStation, args);
    }
}
