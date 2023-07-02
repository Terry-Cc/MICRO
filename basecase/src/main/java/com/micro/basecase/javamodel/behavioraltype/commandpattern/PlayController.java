package com.micro.basecase.javamodel.behavioraltype.commandpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  播放器控制器
 * </p>
 * @since 2023/7/2 10:58
 */
public class PlayController {

    public void execute(Action action) {
        action.execute();
    }
}
