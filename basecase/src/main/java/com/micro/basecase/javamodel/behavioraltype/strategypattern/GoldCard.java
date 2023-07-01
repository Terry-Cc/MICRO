package com.micro.basecase.javamodel.behavioraltype.strategypattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  金卡
 * </p>
 * @since 2023/7/1 18:34
 */
public class GoldCard implements VipCard {

    @Override
    public void discount() {
        System.out.println("金卡会员您好,本次优惠折扣5折!");
    }
}
