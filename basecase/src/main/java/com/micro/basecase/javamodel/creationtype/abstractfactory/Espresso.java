package com.micro.basecase.javamodel.creationtype.abstractfactory;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  意式咖啡
 * </p>
 * @since 2023/7/1 14:46
 */
public class Espresso implements Coffee {

    @Override
    public void show() {
        System.out.println("This is a cup of Espresso !");
    }
}