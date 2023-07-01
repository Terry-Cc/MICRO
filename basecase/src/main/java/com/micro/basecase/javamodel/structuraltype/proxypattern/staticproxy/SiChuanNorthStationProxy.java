package com.micro.basecase.javamodel.structuraltype.proxypattern.staticproxy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  四川北站售票代理点
 * </p>
 * @since 2023/7/1 14:33
 */
@Data
@AllArgsConstructor
public class SiChuanNorthStationProxy implements TrainStation {

    private SiChuanNorthStation siChuanNorthStation;

    @Override
    public void sellTickets() {
        siChuanNorthStation.setFare(siChuanNorthStation.getFare() + 10);
        System.out.println("代理点代理费: 10元");
        siChuanNorthStation.sellTickets();
    }
}
