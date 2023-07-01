package com.micro.basecase.javamodel.structuraltype.proxypattern.cglibtrandaproxy;

import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  上海北站售票处
 * </p>
 * @since 2023/7/1 14:32
 */
@Data
public class ShangHaiNorthStation implements TrainStation {

    @Override
    public void sellTickets() {
        System.out.println("上海北站票价: 1000 元");
    }
}
