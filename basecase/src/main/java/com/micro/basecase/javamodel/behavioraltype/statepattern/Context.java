package com.micro.basecase.javamodel.behavioraltype.statepattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  上下文
 * </p>
 * @since 2023/7/2 12:37
 */
public class Context {

    public static final LifeState START_STATE = new StartState();

    public static final LifeState STOP_STATE = new StopState();

    private LifeState lifeState;

    public void setLifeState(LifeState lifeState) {
        this.lifeState = lifeState;
        this.lifeState.setContext(this);
    }

    public void start () {
        this.lifeState.start();
    }

    public void stop () {
        this.lifeState.stop();
    }
}
