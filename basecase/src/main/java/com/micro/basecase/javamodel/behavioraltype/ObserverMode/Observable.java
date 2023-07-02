package com.micro.basecase.javamodel.behavioraltype.ObserverMode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  被观察者接口
 * </p>
 * @since 2023/7/2 13:04
 */
public interface Observable {

    /**
     * @since 2023/7/2 13:06
     * @description <p>
     *  添加用户(观察者)
     * </p>
     */
    void register(Observer observer);

    /**
     * @since 2023/7/2 13:06
     * @description <p>
     *  推送消息
     * </p>
     */
    void notify(String message);
}
