package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  算数模式
 * </p>
 * @since 2023/7/2 16:26
 */
@AllArgsConstructor
public abstract class ArithmeticExpress implements Expression {

    protected Expression left;

    protected Expression right;
}
