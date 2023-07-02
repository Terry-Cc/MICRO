package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  数字解释器
 * </p>
 * @since 2023/7/2 16:24
 */
@AllArgsConstructor
public class NumberExpress implements Expression {

    private int number;

    @Override
    public int interpret() {
        return number;
    }
}
