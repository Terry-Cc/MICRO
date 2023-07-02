package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  首席技术官
 * </p>
 * @since 2023/7/2 15:27
 */
public class CTO implements IVisitor {

    @Override
    public void visit(Engineer engineer) {
        System.out.println("首席技术官来审查:" + engineer.getName() + "有效代码行数:" + engineer.getCodeLine());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("首席技术官来审查:" + manager.getName() + "项目完成个数:" + manager.getProjectNum());
    }
}
