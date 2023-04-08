package com.micro.jfxexe.common;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * @author XiongJiaMin
 * @apiNote 事件
 * @since 2022-11-18 12:05
 **/
@SuppressWarnings("unused")
public class FXMLNEvent extends Event {

    public static final long serialVersionUID = 596911674194461315L;

    // 是否实时搜索
    private boolean isActualTime;

    public boolean isActualTime() {
        return isActualTime;
    }

    public FXMLNEvent setActualTime(boolean actualTime) {
        isActualTime = actualTime;
        return this;
    }

    public FXMLNEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public FXMLNEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }
}
