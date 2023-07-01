package com.micro.basecase.javamodel.structuraltype.adapterclass;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  适配器测试类
 * </p>
 * @since 2023/7/1 16:06
 */
public class Test {

    public static void main(String[] args){
        AC220 ac220 = new AC220();
        DC5 dc5 = new DC5(ac220);
        System.out.println("交流输出:" + ac220.output() + "V");
        System.out.println("直流转换输出:" + dc5.outputDC5() + "V");
    }
}
