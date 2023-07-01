package com.micro.basecase.javamodel.behavioraltype.strategypattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  银卡
 * </p>
 * @since 2023/7/1 18:35
 */
public class SilverCard implements VipCard {

    @Override
    public void discount() {
        System.out.println("银卡会员您好,本次优惠折扣8折!");
    }
}
