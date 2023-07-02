package com.micro.basecase.javamodel.behavioraltype.mediatorpattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  人员
 * </p>
 * @since 2023/7/2 13:21
 */
@AllArgsConstructor
@Data
public abstract class Person {

    private String name;

    private MediatorCompany mediatorCompany;

    public void getMessage(String message) {
        System.out.println(this.name + "收到消息:" + message);
    }

    public void connect(String message) {
        mediatorCompany.connect(this, message);
    }
}
