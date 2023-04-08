package com.micro.jfxexe.domain;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author XiongJiaMin
 * @apiNote 按钮
 * @since 2022-12-02 13:43
 **/
@SuppressWarnings("unused")
public class AlertF<T> extends Alert {

    private T resultF;

    public AlertF(AlertType alertType) {
        super(alertType);
    }

    public AlertF(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
    }

    public T getResultF() {
        return resultF;
    }

    public void setResultF(T resultF) {
        this.resultF = resultF;
    }
}
