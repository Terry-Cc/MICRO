package com.micro.basecase.javamodel.behavioraltype.chainmode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  责任链模式测试类
 * </p>
 * @since 2023/7/2 11:55
 */
public class Test {

    public static void main(String[] args){
        ChainBuilder chainBuilder = new ChainBuilder();
        chainBuilder.add(new BossHandler())
                .add(new SecretaryHandler())
                .add(new SubordinateHandler())
                .add(new StaffHandler())
                .start();
    }
}
