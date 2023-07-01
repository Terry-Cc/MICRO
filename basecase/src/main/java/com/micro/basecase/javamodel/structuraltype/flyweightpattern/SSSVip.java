package com.micro.basecase.javamodel.structuraltype.flyweightpattern;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  SSSVip会员
 * </p>
 * @since 2023/7/1 18:05
 */
@Data
@AllArgsConstructor
public class SSSVip implements Vip {

    private String id;

    @Override
    public void showId() {
        System.out.println("我的会员编号:" + id);
    }
}
