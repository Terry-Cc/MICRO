package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  计算器
 * </p>
 * @since 2023/7/2 16:35
 */
public class Calculator {

    private Expression left;

    private Expression right;

    private int result;

    public int parse(String expression) {
        String[] split = expression.split("");
        for (int i = 0; i < split.length; i++) {
            String num = split[i];
            if (Operation.isOperator(num)) {
                right = new NumberExpress(Integer.parseInt(split[++i]));
                ArithmeticExpress express = Operation.getExpress(left, right, num);
                if (null == express) {
                    throw new UnsupportedOperationException("Unknown Exception!");
                }
                result = express.interpret();
                left = new NumberExpress(result);
            } else {
                left = new NumberExpress(Integer.parseInt(num));
            }
        }
        return result;
    }
}
