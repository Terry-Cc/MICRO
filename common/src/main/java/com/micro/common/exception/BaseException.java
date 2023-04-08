package com.micro.common.exception;

import com.micro.common.constant.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: www
 * @description: 通用异常类
 * @author: XiongJiaMin
 * @create: 2021-09-26 17:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "通用异常封装")
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 170705201858024989L;

    /**
     * @description 异常一般解释
     * @create: 2021/11/26
     */
    @ApiModelProperty(value = "异常一般解释")
    private String expExplain;

    /**
     * <p>
     *     通用异常父类构造
     *     调用上级构造
     * </p>
     * @param throwable 异常
     * @param expExplain 解释
     */
    public BaseException(Throwable throwable, String expExplain) {
        super(throwable);
        this.expExplain = expExplain;
    }

    /**
     * <p>
     *     无参构造
     *     调用上级
     * </p>
     */
    public BaseException() {
        super();
    };

    /**
     * <p>
     *     简易异常构造, 只有异常
     * </p>
     * @param throwable 异常
     */
    public BaseException(Throwable throwable) {
        this(throwable, Constants.STR_EMPTY);
    }

    /**
     * <p>
     *     简易异常构造, 只有一般解释
     * </p>
     * @param expExplain 解释
     */
    public BaseException(String expExplain) {
        this(null, expExplain);
    }
}
