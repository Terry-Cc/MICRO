package com.micro.basecase.javamodel.behavioraltype.templatemethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  甲方员工
 * </p>
 * @since 2023/7/1 18:24
 */
public class PartyA extends LeaveProcess {

    @Override
    public void apply() {
        System.out.println("2.甲方管理员审批");
    }
}
