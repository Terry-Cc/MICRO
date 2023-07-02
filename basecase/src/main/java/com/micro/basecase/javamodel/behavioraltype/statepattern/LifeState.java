package com.micro.basecase.javamodel.behavioraltype.statepattern;

import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  状态
 * </p>
 * @since 2023/7/2 12:35
 */
@Data
public abstract class LifeState {

    protected Context context;

    public abstract void start();

    public abstract void stop();
}
