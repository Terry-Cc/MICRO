package com.micro.basecase.javamodel.creationtype.factorymethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  工厂方法模式测试类
 * </p>
 * @since 2023/6/30 18:56
 */
public class Test {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Coffee cappuccino = CoffeeFactory.create(Cappuccino.class);
        Coffee mocha = CoffeeFactory.create(Mocha.class);
        cappuccino.show();
        mocha.show();
    }
}
