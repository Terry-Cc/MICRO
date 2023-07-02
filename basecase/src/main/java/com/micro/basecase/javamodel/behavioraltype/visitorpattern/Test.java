package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  访问者模式测试类
 * </p>
 * @since 2023/7/2 15:37
 */
public class Test {

    public static void main(String[] args){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Engineer("JAVA工程师小李"));
        employees.add(new Engineer("JAVA工程师小张"));
        employees.add(new Engineer("JAVA工程师小王"));
        employees.add(new Manager("项目经理小宋"));
        Report report = new Report(employees);
        report.show(new CEO());
        report.show(new CTO());
    }
}
