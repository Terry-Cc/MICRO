package com.micro.common.exception.handler;

import com.micro.common.enums.ResultEnum;
import com.micro.common.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: www
 * @description: 异常处理接口
 * @author: XiongJiaMin
 * @create: 2021-12-01 15:37
 **/
public interface IExceptionHandler {

    /**
     * 获取结果枚举
     * @return ResultEnum
     */
    ResultEnum getResult();

    /**
     * <p>
     *     核心处理方法
     *     对应异常的处理可以继承该类后重写处理方法
     *     反参 Map 封装响应参数
     * </p>
     * @param exception 异常
     * @param request 请求
     */
    Map<String, Object> principal(BaseException exception, HttpServletRequest request);
}
