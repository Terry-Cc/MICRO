package com.micro.basecase.javamodel.behavioraltype.ObserverMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  公众号
 * </p>
 * @since 2023/7/2 13:07
 */
public class Wananchi implements Observable {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : this.observers) {
            observer.update(message);
        }
    }
}
