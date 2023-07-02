package com.micro.basecase.javamodel.behavioraltype.statepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  启动状态
 * </p>
 * @since 2023/7/2 12:39
 */
public class StartState extends LifeState {

    @Override
    public void start() {
        System.out.println("开始启动!");
    }

    @Override
    public void stop() {
        System.out.println("切换停止模式!");
        super.context.setLifeState(Context.STOP_STATE);
        super.context.stop();
    }
}
