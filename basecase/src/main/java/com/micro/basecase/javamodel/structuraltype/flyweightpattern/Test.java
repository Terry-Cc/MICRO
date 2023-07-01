package com.micro.basecase.javamodel.structuraltype.flyweightpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  享元模式测试类
 * </p>
 * @since 2023/7/1 18:11
 */
public class Test {

    public static void main(String[] args){
        BarberShop shop = new BarberShop();
        Vip vip1 = shop.getVip("12345678");
        vip1.showId();
        Vip vip2 = shop.getVip("87654321");
        vip2.showId();
        Vip vip3 = shop.getVip("12345678");
        System.out.println(vip1.hashCode());
        System.out.println(vip2.hashCode());
        System.out.println(vip3.hashCode());
    }
}
