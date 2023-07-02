package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  加法解释器
 * </p>
 * @since 2023/7/2 16:28
 */
public class PlusArithmetic extends ArithmeticExpress implements Expression {

    public PlusArithmetic(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return super.left.interpret() + super.right.interpret();
    }
}
