package com.micro.basecase.javamodel.behavioraltype.templatemethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  请假流程
 * </p>
 * @since 2023/7/1 18:19
 */
public abstract class LeaveProcess {

    private void submit() {
        System.out.println("1.填写请假表");
    }

    public abstract void apply();

    private void leadership() {
        System.out.println("3.直接领导同意");
    }

    private void permission() {
        System.out.println("4.同意请假");
    }

    public void leaveWay() {
        this.submit();
        this.apply();
        this.leadership();
        this.permission();
    }
}
