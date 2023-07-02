package com.micro.basecase.javamodel.behavioraltype.statepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  停止状态
 * </p>
 * @since 2023/7/2 12:41
 */
public class StopState extends LifeState {

    @Override
    public void start() {
        System.out.println("切换启动模式!");
        super.context.setLifeState(Context.START_STATE);
        super.context.start();
    }

    @Override
    public void stop() {
        System.out.println("开始停止!");
    }
}
