package com.micro.basecase.javamodel.structuraltype.facade;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  电灯
 * </p>
 * @since 2023/7/1 17:12
 */
public class Light implements Action {

    @Override
    public void on() {
        System.out.println("我打开了灯!");
    }

    @Override
    public void off() {
        System.out.println("我关闭了灯!");
    }
}
