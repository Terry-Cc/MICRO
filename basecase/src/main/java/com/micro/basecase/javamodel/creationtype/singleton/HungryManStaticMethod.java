package com.micro.basecase.javamodel.creationtype.singleton;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  饿汉实现线程安全单例(静态代码块)
 * </p>
 * @since 2023/5/22 21:33
 */
public class HungryManStaticMethod {

    private static HungryManStaticMethod instance;

    static {
        instance = new HungryManStaticMethod();
    }

    private HungryManStaticMethod () {}

    public static HungryManStaticMethod getInstance() {
        return instance;
    }
}
