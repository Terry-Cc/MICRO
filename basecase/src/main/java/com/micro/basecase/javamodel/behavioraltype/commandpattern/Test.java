package com.micro.basecase.javamodel.behavioraltype.commandpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  命令模式测试类
 * </p>
 * @since 2023/7/2 11:00
 */
public class Test {

    public static void main(String[] args) {
        PlayController playController = new PlayController();
        playController.execute(new StartAction(new Player()));
        playController.execute(new StopAction(new Player()));
    }
}
