package com.micro.basecase.javamodel.structuraltype.proxypattern.cglibtrandaproxy;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  cglib代理测试类
 * </p>
 * @since 2023/7/1 15:50
 */
public class Test {

    public static void main(String[] args){
        ShangHaiNorthStation shangHaiNorthStation = new ShangHaiNorthStation();
        shangHaiNorthStation.sellTickets();
        System.out.println("-------------------------------------------");
        StationCglibProxy stationCglibProxy = new StationCglibProxy();
        TrainStation proxy = stationCglibProxy.getProxy(ShangHaiNorthStation.class);
        proxy.sellTickets();
    }
}
