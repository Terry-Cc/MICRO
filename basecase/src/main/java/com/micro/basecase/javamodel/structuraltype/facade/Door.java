package com.micro.basecase.javamodel.structuraltype.facade;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  门
 * </p>
 * @since 2023/7/1 17:13
 */
public class Door implements Action {

    @Override
    public void on() {
        System.out.println("我打开了门!");
    }

    @Override
    public void off() {
        System.out.println("我关闭了门!");
    }
}
