package com.micro.basecase.singleton;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  饿汉模式实现线程安全单例
 * </p>
 * @since 2023/5/22 21:31
 */
public class HungryMan {

    private HungryMan () {}

    private final static HungryMan INSTANCE = new HungryMan();

    public static HungryMan getInstance() {
        return INSTANCE;
    }
}
