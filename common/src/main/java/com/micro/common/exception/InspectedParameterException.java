package com.micro.common.exception;

import lombok.EqualsAndHashCode;

/**
 * @program: www
 * @description: 受检查参数异常
 * @author: XiongJiaMin
 * @create: 2021-11-05 17:33
 **/
@EqualsAndHashCode(callSuper = true)
public class InspectedParameterException extends BaseException {

    private static final long serialVersionUID = -7767728381721701014L;

    public InspectedParameterException (String expExplain) {
        super(expExplain);
    }

    public InspectedParameterException (Throwable throwable, String expExplain) {
        super(throwable, expExplain);
    }

    public InspectedParameterException (Throwable throwable) {
        super(throwable);
    }
}
