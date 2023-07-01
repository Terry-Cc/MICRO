package com.micro.basecase.javamodel.structuraltype.decoratorpattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  炒饭老板
 * </p>
 * @since 2023/7/1 16:23
 */
@AllArgsConstructor
public abstract class FiredRiceMaker extends FiredRice {

    private FiredRice firedRice;

    @Override
    public String getBatching() {
        return this.firedRice.getBatching();
    }

    @Override
    public int getPrice() {
        return this.firedRice.getPrice();
    }
}
