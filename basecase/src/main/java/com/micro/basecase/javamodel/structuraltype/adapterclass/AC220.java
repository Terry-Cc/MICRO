package com.micro.basecase.javamodel.structuraltype.adapterclass;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  220V交流电
 * </p>
 * @since 2023/7/1 16:00
 */
public class AC220 implements Power {

    public int output() {
        return 220;
    }
}
