package com.micro.basecase.javamodel.structuraltype.facade;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  电风扇
 * </p>
 * @since 2023/7/1 17:11
 */
public class Fan implements Action {

    @Override
    public void on() {
        System.out.println("我打开了电风扇!");
    }

    @Override
    public void off() {
        System.out.println("我关闭了电风扇!");
    }
}
