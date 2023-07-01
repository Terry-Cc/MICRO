package com.micro.basecase.javamodel.structuraltype.decoratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  鸡蛋
 * </p>
 * @since 2023/7/1 16:25
 */
public class Egg extends FiredRiceMaker {

    public Egg(FiredRice firedRice) {
        super(firedRice);
    }

    @Override
    public String getBatching() {
        return super.getBatching() + "加鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
