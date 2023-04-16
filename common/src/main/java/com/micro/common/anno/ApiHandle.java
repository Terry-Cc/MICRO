package com.micro.common.anno;

import com.micro.common.constant.Constants;
import java.lang.annotation.*;

/**
 * @program: www
 * @description: 接口拦截处理
 * @author: XiongJiaMin
 * @create: 2021-09-26 17:47
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface ApiHandle {

    /**
     * @description 接口访问等级限制
     * @create: 2021/9/26
     */
    String level() default Constants.API_LEVEL_LOW;
}
