package com.micro.basecase.javamodel.structuraltype.proxypattern.staticproxy;

import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  四川东站售票处
 * </p>
 * @since 2023/7/1 14:32
 */
@Data
public class SiChuanNorthStation implements TrainStation {

    /**
     * @since 2023/7/1 14:29
     * @description <p>
     *  票价
     * </p>
     */
    private int fare = 100;

    @Override
    public void sellTickets() {
        System.out.println("四川东站实收票价:" + fare + "元");
    }
}
