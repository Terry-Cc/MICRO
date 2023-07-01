package com.micro.basecase.javamodel.structuraltype.facade;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  电视
 * </p>
 * @since 2023/7/1 17:10
 */
public class Tv implements Action {

    @Override
    public void on() {
        System.out.println("我打开了电视!");
    }

    @Override
    public void off() {
        System.out.println("我关闭了电视!");
    }
}
