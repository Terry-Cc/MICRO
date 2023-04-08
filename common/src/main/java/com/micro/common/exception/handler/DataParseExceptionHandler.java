package com.micro.common.exception.handler;

import com.micro.common.enums.ResultEnum;
import com.micro.common.exception.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: www
 * @description: 数据转换异常处理
 * @author: XiongJiaMin
 * @create: 2021-11-29 15:08
 **/
public class DataParseExceptionHandler extends BaseExceptionHandler {

    private final static Logger logger = LogManager.getLogger(DataParseExceptionHandler.class);

    @Override
    public ResultEnum getResult() {
        return ResultEnum.COMMON_PARSE_EXP;
    }

    @Override
    public Map<String, Object> principal(BaseException exception, HttpServletRequest request) {
        logger.info("数据转换异常Handler处理中...");
        logger.error(exception);
        return super.principal(exception, request);
    }
}
