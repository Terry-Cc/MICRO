package com.micro.jfxexe.annotation;

import de.felixroske.jfxsupport.AbstractFxmlView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author XiongJiaMin
 * @apiNote 跳转
 * @since 2022-11-28 15:26
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FXMLJump {

    Class<? extends AbstractFxmlView> jumpView();
}
