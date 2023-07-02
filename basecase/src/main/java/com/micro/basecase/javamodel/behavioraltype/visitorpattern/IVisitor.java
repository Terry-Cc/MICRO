package com.micro.basecase.javamodel.behavioraltype.visitorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  访问者抽象
 * </p>
 * @since 2023/7/2 14:45
 */
public interface IVisitor {

    void visit(Engineer engineer);

    void visit(Manager manager);
}
