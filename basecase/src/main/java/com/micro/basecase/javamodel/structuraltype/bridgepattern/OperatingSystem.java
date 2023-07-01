package com.micro.basecase.javamodel.structuraltype.bridgepattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  操作系统
 * </p>
 * @since 2023/7/1 16:51
 */
@AllArgsConstructor
public abstract class OperatingSystem {

    protected Video video;

    public abstract void play(String fileName);
}
