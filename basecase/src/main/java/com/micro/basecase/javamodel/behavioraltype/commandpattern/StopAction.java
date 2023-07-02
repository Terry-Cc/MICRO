package com.micro.basecase.javamodel.behavioraltype.commandpattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  停止事件
 * </p>
 * @since 2023/7/2 10:57
 */
@AllArgsConstructor
public class StopAction implements Action {

    private Player player;

    @Override
    public void execute() {
        player.stop();
    }
}
