package com.micro.basecase.javamodel.structuraltype.proxypattern.staticproxy;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  静态代理测试类
 * </p>
 * @since 2023/7/1 14:35
 */
public class Test {

    public static void main(String[] args){
        SiChuanNorthStation siChuanNorthStation = new SiChuanNorthStation();
        siChuanNorthStation.sellTickets();
        System.out.println("----------------------------------------");
        SiChuanNorthStationProxy stationProxy = new SiChuanNorthStationProxy(siChuanNorthStation);
        stationProxy.sellTickets();
    }
}
