package com.micro.jfxexe.annotation;

import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.handler.DefaultEventHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author XiongJiaMin
 * @apiNote 事件
 * @since 2022-11-24 13:25
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FXMLEvent {

    FXMLEventType eventType() default FXMLEventType.DEFAULT_TYPE;

    Class<? extends DefaultEventHandler> handler() default DefaultEventHandler.class;
}
