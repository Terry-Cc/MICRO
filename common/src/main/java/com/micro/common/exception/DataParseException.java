package com.micro.common.exception;

import lombok.EqualsAndHashCode;

/**
 * @program: www
 * @description: 数据解析异常
 * @author: XiongJiaMin
 * @create: 2021-11-24 10:03
 **/
@EqualsAndHashCode(callSuper = true)
public class DataParseException extends BaseException {

    private static final long serialVersionUID = 3980614246687382879L;

    public DataParseException (String expExplain) {
        super(expExplain);
    }

    public DataParseException (Throwable throwable, String expExplain) {
        super(throwable, expExplain);
    }

    public DataParseException (Throwable throwable) {
        super(throwable);
    }
}
