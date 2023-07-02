package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

import lombok.Data;

import java.util.Random;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  员工
 * </p>
 * @since 2023/7/2 15:25
 */
@Data
public abstract class Employee {

    private String name;

    private int kpi;

    public Employee (String name) {
        this.kpi = new Random().nextInt(10);
        this.name = name;
    }

    public abstract void accept(IVisitor iVisitor);
}
