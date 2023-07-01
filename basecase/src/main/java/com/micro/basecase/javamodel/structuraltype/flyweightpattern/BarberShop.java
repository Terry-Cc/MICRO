package com.micro.basecase.javamodel.structuraltype.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  理发店
 * </p>
 * @since 2023/7/1 18:08
 */
public class BarberShop {

    private Map<String, Vip> vips = new HashMap<>();

    public Vip getVip(String id) {
        if (vips.containsKey(id)) {
            return vips.get(id);
        }
        SSSVip sssVip = new SSSVip(id);
        vips.put(sssVip.getId(), sssVip);
        return sssVip;
    }
}
