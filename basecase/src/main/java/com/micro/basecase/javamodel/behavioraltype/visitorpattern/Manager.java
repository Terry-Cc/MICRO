package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

import java.util.Random;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  项目经理
 * </p>
 * @since 2023/7/2 15:22
 */
public class Manager extends Employee {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(IVisitor iVisitor) {
        iVisitor.visit(this);
    }

    public int getProjectNum() {
        return new Random().nextInt(10);
    }
}
