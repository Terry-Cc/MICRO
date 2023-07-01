package com.micro.basecase.javamodel.creationtype.builder;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  创建模式测试类
 * </p>
 * @since 2023/6/30 19:36
 */
public class Test {

    public static void main(String[] args){
        Computer myComputer = new ComputerBuilder()
                .init()
                .addMotherBoard("Extreme主板")
                .addCpu("Inter 12900K")
                .addMemory("芝奇幻峰戟 16G*2")
                .addDisk("三星980Pro 2T")
                .addGpu("华硕3090Ti 水猛禽")
                .addPower("雷神二代1200W")
                .addHeatSink("龙神二代一体式水冷")
                .addChassis("太阳神机箱")
                .build();
        System.out.println(myComputer);
    }
}
