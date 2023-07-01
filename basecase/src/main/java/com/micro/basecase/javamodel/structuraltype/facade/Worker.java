package com.micro.basecase.javamodel.structuraltype.facade;

import java.util.Scanner;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  打工仔
 * </p>
 * @since 2023/7/1 17:14
 */
public class Worker {

    private Door door;

    private Light light;

    private Fan fan;

    private Tv tv;

    public Worker () {
        this.door = new Door();
        this.light = new Light();
        this.fan = new Fan();
        this.tv = new Tv();
    }

    private void goToWork() {
        System.out.println("去上班了!");
        fan.off();
        tv.off();
        light.off();
        door.off();
        System.out.println("emo~emo~");
    }

    private void goOffWork() {
        System.out.println("终于下班了!");
        door.on();
        light.on();
        tv.on();
        fan.on();
        System.out.println("爽歪歪!");
    }

    public void doSomething() {
        System.out.print("上班还是下班:");
        String someThing = new Scanner(System.in).next();
        switch (someThing) {
            case "上班":
                this.goToWork();
                break;
            case "下班":
                this.goOffWork();
                break;
             default:
                 System.out.println("听不懂啊!");
        }
    }
}
