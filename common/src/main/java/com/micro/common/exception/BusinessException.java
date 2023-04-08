package com.micro.common.exception;

import lombok.EqualsAndHashCode;

/**
 * @program: www
 * @description: 服务异常
 * @author: XiongJiaMin
 * @create: 2021-10-29 14:28
 **/
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -9155526389274175146L;

    public BusinessException (Throwable throwable, String expExplain) {
        super(throwable, expExplain);
    }

    public BusinessException (String expExplain) {
        super(expExplain);
    }

    public BusinessException (Throwable throwable) {
        super(throwable);
    }
}
