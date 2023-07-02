package com.micro.basecase.javamodel.behavioraltype.interpreterpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  操作判断
 * </p>
 * @since 2023/7/2 16:38
 */
public class Operation {

    public static boolean isOperator(String symbol) {
        return "+".equals(symbol) || "-".equals(symbol) || "*".equals(symbol) || "/".equals(symbol);
    }

    public static ArithmeticExpress getExpress(Expression left, Expression right, String symbol) {
        switch (symbol) {
            case "+":
                return new PlusArithmetic(left, right);
            case "-":
                return new MinusArithmetic(left, right);
            case "*":
                return new MclArithmetic(left, right);
            case "/":
                return new DivisionArithmetic(left, right);
            default:
                return null;
        }
    }
}
