package com.micro.basecase.javamodel.creationtype.abstractfactory;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  工厂生产咖啡基类
 * </p>
 * @since 2023/6/30 18:50
 */
public interface Coffee {

    void show();

    /**
     * @since 2023/6/30 18:53
     * @description <p>
     *  美式咖啡
     * </p>
     */
    class CaffèAmericano implements Coffee {

        @Override
        public void show() {
            System.out.println("This is a cup of Caffè Americano !");
        }
    }

    /**
     * @since 2023/6/30 18:53
     * @description <p>
     *  意式咖啡
     * </p>
     */
    class Espresso implements Coffee {

        @Override
        public void show() {
            System.out.println("This is a cup of Espresso !");
        }
    }
}
