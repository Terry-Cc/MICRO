package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  减法解释器
 * </p>
 * @since 2023/7/2 16:30
 */
public class MinusArithmetic extends ArithmeticExpress {

    public MinusArithmetic(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return super.left.interpret() - super.right.interpret();
    }
}
