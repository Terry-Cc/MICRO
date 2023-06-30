package com.micro.basecase.javamodel.creationtype.factorymethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  咖啡生产工厂
 * </p>
 * @since 2023/6/30 18:54
 */
public final class CoffeeFactory {

    public static Coffee create(Class<? extends Coffee> clz) throws IllegalAccessException, InstantiationException {
        if (null != clz) {
            return clz.newInstance();
        }
        return null;
    }
}
