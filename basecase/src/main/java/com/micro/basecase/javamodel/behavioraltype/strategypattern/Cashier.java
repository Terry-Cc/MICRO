package com.micro.basecase.javamodel.behavioraltype.strategypattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  收银台
 * </p>
 * @since 2023/7/1 18:35
 */
public class Cashier {

    private static Map<String, VipCard> cardMap = new HashMap<>();

    static {
        cardMap.put("gold", new GoldCard());
        cardMap.put("silver", new SilverCard());
    }

    public VipCard getCard(String type) {
        return cardMap.get(type);
    }
}
