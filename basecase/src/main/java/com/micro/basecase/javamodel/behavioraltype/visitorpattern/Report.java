package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  引导类
 * </p>
 * @since 2023/7/2 15:38
 */
@AllArgsConstructor
public class Report {

    private List<Employee> employees;

    public void show(IVisitor visitor) {
        for (Employee employee : this.employees) {
            employee.accept(visitor);
        }
    }
}
