package com.micro.common.exception.handler;

import com.micro.common.constant.Constants;
import com.micro.common.exception.BaseException;
import com.micro.common.exception.BusinessException;
import com.micro.common.exception.DataParseException;
import com.micro.common.exception.InspectedParameterException;
import com.micro.common.util.other.CommonUtils;
import com.micro.common.web.vo.CommResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: www
 * @description: 异常分发器
 * @author: XiongJiaMin
 * @create: 2021-12-01 15:46
 **/
@RestControllerAdvice
public class ExceptionHandlerDistributor {

    private final static Logger logger = LogManager.getLogger(ExceptionHandlerDistributor.class);

    private final Map<String, IExceptionHandler> handlers = new HashMap<>();

    /**
     * 初始化异常处理器, 以便发生异常时调用
     * 异常名使用异常处理器普通名
     */
    @PostConstruct
    private void init () {
        handlers.put(BaseException.class.getSimpleName().concat(Constants.SUFFIX_EXP), new BaseExceptionHandler());
        handlers.put(BusinessException.class.getSimpleName().concat(Constants.SUFFIX_EXP), new BusinessExceptionHandler());
        handlers.put(DataParseException.class.getSimpleName().concat(Constants.SUFFIX_EXP), new DataParseExceptionHandler());
        handlers.put(InspectedParameterException.class.getSimpleName().concat(Constants.SUFFIX_EXP), new InspectedParameterExceptionHandler());
    }

    /**
     * <p>
     *     获取异常处理器 根据 异常名 加 后缀 Handler
     *     获取失败使用默认 BaseExceptionHandler
     * </p>
     * @param exception 异常
     * @return 异常处理器
     */
    private IExceptionHandler getHandler (BaseException exception) {
        IExceptionHandler handler = handlers.get(exception.getClass().getSimpleName().concat(Constants.SUFFIX_EXP));
        if (CommonUtils.isEmpty(handler)) {
            logger.error("The {} can not be found in exception handlers ! please add first !", exception.getClass().getSimpleName());
            handler = handlers.get(BaseException.class.getSimpleName().concat(Constants.SUFFIX_EXP));
        }
        return handler;
    }

    /**
     * <p>
     *     统一异常处理
     *     获取到异常处理器后执行Handler
     * </p>
     * @param exception 异常
     * @param request 请求
     * @return 响应参数
     */
    @ExceptionHandler(BaseException.class)
    public CommResponse handle(BaseException exception, HttpServletRequest request) {
        IExceptionHandler handler = getHandler(exception);
        return new CommResponse(handler.getResult(), handler.principal(exception, request));
    }
}
