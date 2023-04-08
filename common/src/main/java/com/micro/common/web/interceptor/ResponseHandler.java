package com.micro.common.web.interceptor;

import com.micro.common.anno.ApiHandle;
import com.micro.common.anno.ApiHandleAll;
import com.micro.common.util.other.CommonUtils;
import com.micro.common.web.util.WebUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 *     在当次请求没有异常的情况下才会生效
 *     只针对有 @ResponseBody 的API
 *     否正走异常处理器
 *     由于泛用性不强不使用
 * </p>
 * @program: www
 * @description: 响应处理类
 * @author: XiongJiaMin
 * @create: 2021-09-28 16:00
 **/
// @ControllerAdvice(basePackages = {"com.roboot.www.web.controller"})
// @Order(value = 10002)
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    private final static Logger logger = LogManager.getLogger(ResponseHandler.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return CommonUtils.isNotEmpty(returnType.getDeclaringClass().getAnnotation(ApiHandleAll.class))
                || CommonUtils.isNotEmpty(returnType.getMethodAnnotation(ApiHandle.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        logger.info("响应拦截: {}", WebUtil::getUrl);
        return body;
    }
}
