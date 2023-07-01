package com.micro.basecase.javamodel.structuraltype.proxypattern.jdktrandaproxy;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  JDK动态代理测试类
 * </p>
 * @since 2023/7/1 15:09
 */
public class Test {

    public static void main(String[] args){
        GuangDongNorthStation guangDongNorthStation = new GuangDongNorthStation();
        guangDongNorthStation.sellTickets();
        System.out.println("----------------------------------------");
        StationJdkProxy stationJdkProxy = new StationJdkProxy();
        TrainStation proxy = stationJdkProxy.getProxy(guangDongNorthStation);
        proxy.sellTickets();
    }
}
