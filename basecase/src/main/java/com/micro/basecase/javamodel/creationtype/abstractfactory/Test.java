package com.micro.basecase.javamodel.creationtype.abstractfactory;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  抽象工厂模式测试类
 * </p>
 * @since 2023/6/30 18:56
 */
public class Test {

    public static void main(String[] args)  {
        Coffee caffèAmericano = new AmericanCoffeeFactory().create();
        Coffee espresso = new EspressoFactory().create();
        caffèAmericano.show();
        espresso.show();
    }
}
