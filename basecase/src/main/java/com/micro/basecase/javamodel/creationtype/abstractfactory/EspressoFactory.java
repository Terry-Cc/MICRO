package com.micro.basecase.javamodel.creationtype.abstractfactory;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  意式咖啡工厂
 * </p>
 * @since 2023/7/1 14:48
 */
public class EspressoFactory implements CoffeeFactory {

    @Override
    public Coffee create() {
        return new Espresso();
    }
}
