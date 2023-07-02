package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

import java.util.Random;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  工程师
 * </p>
 * @since 2023/7/2 15:22
 */
public class Engineer extends Employee{

    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(IVisitor iVisitor) {
        iVisitor.visit(this);
    }

    public int getCodeLine() {
        return new Random().nextInt(10000);
    }
}
