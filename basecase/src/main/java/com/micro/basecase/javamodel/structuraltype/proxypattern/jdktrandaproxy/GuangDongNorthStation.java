package com.micro.basecase.javamodel.structuraltype.proxypattern.jdktrandaproxy;

import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  广东北站售票处
 * </p>
 * @since 2023/7/1 14:32
 */
@Data
public class GuangDongNorthStation implements TrainStation {

    @Override
    public void sellTickets() {
        System.out.println("广东北站票价: 100 元");
    }
}
