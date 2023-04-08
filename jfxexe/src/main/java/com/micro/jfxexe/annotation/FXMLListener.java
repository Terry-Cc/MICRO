package com.micro.jfxexe.annotation;

import com.micro.jfxexe.common.FXMLListenerType;
import com.micro.jfxexe.listener.DefaultEventListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author XiongJiaMin
 * @apiNote 监听器
 * @since 2022-11-24 13:25
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FXMLListener {

    FXMLListenerType listenerType();

    Class<? extends DefaultEventListener<?>> listener();
}
