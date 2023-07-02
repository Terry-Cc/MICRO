package com.micro.basecase.javamodel.behavioraltype.statepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  状态机模式测试类
 * </p>
 * @since 2023/7/2 12:44
 */
public class Test {

    public static void main(String[] args){
        Context context = new Context();
        context.setLifeState(Context.START_STATE);
        context.start();
        context.stop();
    }
}
