package com.micro.basecase.javamodel.behavioraltype.mediatorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  租客
 * </p>
 * @since 2023/7/2 13:24
 */
public class Tenant extends Person {

    public Tenant(String name, MediatorCompany mediatorCompany) {
        super(name, mediatorCompany);
    }
}
