package com.micro.basecase.javamodel.structuraltype.decoratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  装饰器模式测试类
 * </p>
 * @since 2023/7/1 16:29
 */
public class Test {

    public static void main(String[] args){
        FiredRice firedRice = new FiredRice();
        // 普通炒饭
        System.out.println(firedRice);
        // 普通炒饭加一个鸡蛋
        firedRice = new Egg(firedRice);
        System.out.println(firedRice);
        // 普通炒饭加两个鸡蛋
        firedRice = new Egg(firedRice);
        System.out.println(firedRice);
        // 普通炒饭加两个鸡蛋和一个火腿
        firedRice = new Ham(firedRice);
        System.out.println(firedRice);
    }
}
