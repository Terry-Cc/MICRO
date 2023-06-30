package com.micro.basecase.javamodel.creationtype.abstractfactory;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  咖啡生产工厂
 * </p>
 * @since 2023/6/30 18:54
 */
public interface CoffeeFactory {

    Coffee create();

    /**
     * @since 2023/6/30 19:12
     * @description <p>
     *  美式咖啡工厂
     * </p>
     */
    class AmericanCoffeeFactory implements CoffeeFactory {

        @Override
        public Coffee create() {
            return new Coffee.CaffèAmericano();
        }
    }

    /**
     * @since 2023/6/30 19:12
     * @description <p>
     *  意式咖啡工厂
     * </p>
     */
    class EspressoFactory implements CoffeeFactory {

        @Override
        public Coffee create() {
            return new Coffee.Espresso();
        }
    }
}
