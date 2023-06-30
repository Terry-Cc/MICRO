package com.micro.basecase.javamodel.creationtype.factorymethod;

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
     *  卡普契诺咖啡
     * </p>
     */
    class Cappuccino implements Coffee {

        @Override
        public void show() {
            System.out.println("This is a cup of Cappuccino !");
        }
    }

    /**
     * @since 2023/6/30 18:53
     * @description <p>
     *  摩卡咖啡
     * </p>
     */
    class Mocha implements Coffee {

        @Override
        public void show() {
            System.out.println("This is a cup of Mocha !");
        }
    }
}
