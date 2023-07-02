package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  解释器模式测试类
 * </p>
 * @since 2023/7/2 16:52
 */
public class Test {

    public static void main(String[] args){
        Calculator calculator = new Calculator();
        String cal = "1+5-3*8/4";
        System.out.println(cal + " = " + calculator.parse(cal));
    }
}
