package com.micro.basecase.javamodel.behavioraltype.chainmode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  责任链处理器
 * </p>
 * @since 2023/7/2 11:16
 */
public abstract class Handler {

    private Handler next;

    public void next(Handler next) {
        this.next = next;
    }
    public Handler next() {
        return next;
    }

    public abstract void handler();
}
