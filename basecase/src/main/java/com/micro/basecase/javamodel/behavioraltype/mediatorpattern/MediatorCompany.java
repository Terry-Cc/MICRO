package com.micro.basecase.javamodel.behavioraltype.mediatorpattern;

import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  中介公司
 * </p>
 * @since 2023/7/2 13:22
 */
@Data
public class MediatorCompany {

    private HouseOwner houseOwner;

    private Tenant tenant;

    public void connect(Person person, String message) {
        if (houseOwner == person) {
            tenant.getMessage(message);
        } else {
            houseOwner.getMessage(message);
        }
    }
}
