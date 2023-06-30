package com.micro.basecase.javamodel.creationtype.singleton;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  静态内部类实现线程安全单例
 * </p>
 * @since 2023/5/22 21:27
 */
public class StaticInternalClass {

    private StaticInternalClass() {}

    private static class StaticInternalClassHolder {
        private final static StaticInternalClass INSTANCE = new StaticInternalClass();
    }

    public static StaticInternalClass getInstance () {
        return StaticInternalClassHolder.INSTANCE;
    }
}
