package com.micro.basecase.singleton;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  双检锁实现线程安全单例
 * </p>
 * @since 2023/5/22 21:42
 */
public class DoubleCheckLock {

    private volatile static DoubleCheckLock instance;

    private DoubleCheckLock () {}

    public static DoubleCheckLock getInstance() {
        if (null == instance) {
            synchronized (DoubleCheckLock.class) {
                if (null == instance) {
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
