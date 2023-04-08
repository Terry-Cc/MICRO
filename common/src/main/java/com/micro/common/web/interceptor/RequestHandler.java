package com.micro.common.web.interceptor;

import com.micro.common.anno.ApiHandle;
import com.micro.common.anno.ApiHandleAll;
import com.micro.common.util.other.CommonUtils;
import com.micro.common.web.util.WebUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * <p>
 *     只针对有 @RequestBody 的API
 *     由于泛用性不强不使用
 * </p>
 * @program: www
 * @description: 请求处理类
 * @author: XiongJiaMin
 * @create: 2021-09-28 15:58
 **/
// @ControllerAdvice(basePackages = {"com.roboot.www.web.controller"})
// @Order(value = 10001)
public class RequestHandler implements RequestBodyAdvice {

    private final static Logger logger = LogManager.getLogger(RequestHandler.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return CommonUtils.isNotEmpty(methodParameter.getDeclaringClass().getAnnotation(ApiHandleAll.class))
                || CommonUtils.isNotEmpty(methodParameter.getMethodAnnotation(ApiHandle.class));
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        logger.info("请求拦截: {}", WebUtil.getUrl());
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
