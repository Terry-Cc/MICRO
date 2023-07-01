package com.micro.basecase.javamodel.behavioraltype.strategypattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  策略模式测试类
 * </p>
 * @since 2023/7/1 18:39
 */
public class Test {

    public static void main(String[] args){
        Cashier cashier = new Cashier();
        VipCard gold = cashier.getCard("gold");
        gold.discount();
        System.out.println();
        VipCard silver = cashier.getCard("silver");
        silver.discount();
    }
}
