package com.micro.common.exception.handler;

import com.micro.common.constant.Constants;
import com.micro.common.enums.ResultEnum;
import com.micro.common.exception.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: www
 * @description: 默认异常处理
 * @author: XiongJiaMin
 * @create: 2021-06-18 14:49
 **/
public class BaseExceptionHandler implements IExceptionHandler {

    private final static Logger logger = LogManager.getLogger(BaseExceptionHandler.class);

    @Override
    public ResultEnum getResult() {
        return ResultEnum.COMMON_UNKNOWN_EXP;
    }

    @Override
    public Map<String, Object> principal (BaseException exception, HttpServletRequest request) {
        logger.info("未知异常Handler处理中...");
        Map<String, Object> data = new HashMap<>();
        data.put(Constants.EXP_EXPLAIN, exception.getExpExplain());
        data.put(Constants.EXP_DETAIL, exception.getMessage());
        return data;
    }
}
