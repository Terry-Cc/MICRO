package com.micro.common.anno;

import com.micro.common.constant.Constants;
import org.springframework.core.annotation.AliasFor;
import java.lang.annotation.*;

/**
 * @program: www
 * @description: 接口拦截处理全局
 * @author: XiongJiaMin
 * @create: 2021-09-28 17:54
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ApiHandle
public @interface ApiHandleAll {

    /**
     * @description 接口访问等级限制
     * @create: 2021/9/26
     */
    @AliasFor(annotation = ApiHandle.class)
    String level() default Constants.LOG_LEVEL_LOW;
}
