package com.micro.common.exception.handler;

import com.micro.common.enums.ResultEnum;
import com.micro.common.exception.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: www
 * @description: 服务异常处理
 * @author: XiongJiaMin
 * @create: 2021-11-26 17:57
 **/
public class BusinessExceptionHandler extends BaseExceptionHandler {

    private final static Logger logger = LogManager.getLogger(BusinessExceptionHandler.class);

    @Override
    public ResultEnum getResult() {
        return ResultEnum.COMMON_SERVICE_EXP;
    }

    @Override
    public Map<String, Object> principal(BaseException exception, HttpServletRequest request) {
        logger.info("服务异常Handler处理中...");
        logger.error(exception.getCause());
        return super.principal(exception, request);
    }
}
