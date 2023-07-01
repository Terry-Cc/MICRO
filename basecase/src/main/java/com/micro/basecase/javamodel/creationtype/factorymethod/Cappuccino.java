package com.micro.basecase.javamodel.creationtype.factorymethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  卡普契诺咖啡
 * </p>
 * @since 2023/7/1 14:53
 */
public class Cappuccino implements Coffee {

    @Override
    public void show() {
        System.out.println("This is a cup of Cappuccino !");
    }
}
