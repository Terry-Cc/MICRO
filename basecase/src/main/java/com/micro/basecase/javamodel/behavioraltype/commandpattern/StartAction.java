package com.micro.basecase.javamodel.behavioraltype.commandpattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  启动事件
 * </p>
 * @since 2023/7/2 10:56
 */
@AllArgsConstructor
public class StartAction implements Action {

    private Player player;

    @Override
    public void execute() {
        player.start();
    }
}
