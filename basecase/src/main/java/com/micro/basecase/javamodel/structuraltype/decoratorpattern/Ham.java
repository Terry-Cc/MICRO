package com.micro.basecase.javamodel.structuraltype.decoratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  火腿
 * </p>
 * @since 2023/7/1 16:26
 */
public class Ham extends FiredRiceMaker {

    public Ham(FiredRice firedRice) {
        super(firedRice);
    }

    @Override
    public String getBatching() {
        return super.getBatching() + "加火腿";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 3;
    }
}
