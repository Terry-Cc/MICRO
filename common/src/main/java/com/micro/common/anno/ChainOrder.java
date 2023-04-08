package com.micro.common.anno;

import java.lang.annotation.*;

/**
 * @author XiongJiaMin
 * @apiNote 责任链标识
 * @since 2023-01-03 16:56
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChainOrder {

    /**
     * 优先级配置, 数值越小优先级越高
     */
    int order() default 1;

    /**
     * 责任链名称, 根据名称分组
     */
    String name() default "default";
}
