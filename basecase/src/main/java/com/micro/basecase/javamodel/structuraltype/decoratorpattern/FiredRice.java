package com.micro.basecase.javamodel.structuraltype.decoratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  炒饭
 * </p>
 * @since 2023/7/1 16:19
 */
public class FiredRice {

    /**
     * @since 2023/7/1 16:27
     * @description <p>
     *  配料
     * </p>
     */
    public String getBatching() {
        return "炒饭";
    }

    /**
     * @since 2023/7/1 16:27
     * @description <p>
     *  总价
     * </p>
     */
    public int getPrice() {
        return 6;
    }

    @Override
    public String toString() {
        return this.getBatching() + ":" + this.getPrice() + "元";
    }
}
