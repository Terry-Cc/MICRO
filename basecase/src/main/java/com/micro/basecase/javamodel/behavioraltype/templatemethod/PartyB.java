package com.micro.basecase.javamodel.behavioraltype.templatemethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  乙方员工
 * </p>
 * @since 2023/7/1 18:25
 */
public class PartyB extends LeaveProcess {

    @Override
    public void apply() {
        System.out.println("2.乙方管理员审批");
    }
}
