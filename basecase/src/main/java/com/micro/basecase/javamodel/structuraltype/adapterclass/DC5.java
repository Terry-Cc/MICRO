package com.micro.basecase.javamodel.structuraltype.adapterclass;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  220V交流转5V直流
 * </p>
 * @since 2023/7/1 16:03
 */
@AllArgsConstructor
public class DC5 implements AdapterDC5 {

    private Power power;

    @Override
    public int outputDC5() {
        int ac220 = power.output();
        int dc5 = ac220 / 44;
        return dc5;
    }
}
