package com.micro.common.anno;

import com.micro.common.constant.Constants;

import java.lang.annotation.*;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  接口日志捕获
 * </p>
 * @since 2023/4/16 16:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface ApiLogHandler {

    /**
     * @description 接口日志捕获模式
     * @create: 2023/4/16 16:02
     */
    String model() default Constants.API_LOG_MODEL_NORMAL;
}
