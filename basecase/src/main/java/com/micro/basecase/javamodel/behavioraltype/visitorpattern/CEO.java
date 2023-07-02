package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  总裁
 * </p>
 * @since 2023/7/2 15:26
 */
public class CEO implements IVisitor {

    @Override
    public void visit(Engineer engineer) {
        System.out.println("总裁来审查:" + engineer.getName() + "KPI:" + engineer.getKpi());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("总裁来审查:" + manager.getName() + "KPI:" + manager.getKpi());
    }
}
