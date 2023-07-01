package com.micro.basecase.javamodel.behavioraltype.templatemethod;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  模板方法模式测试类
 * </p>
 * @since 2023/7/1 18:27
 */
public class Test {

    public static void main(String[] args){
        PartyA partyA = new PartyA();
        PartyB partyB = new PartyB();
        System.out.println("甲方员工请假流程:");
        partyA.leaveWay();
        System.out.println();
        System.out.println("乙方员工请假流程:");
        partyB.leaveWay();
    }
}
