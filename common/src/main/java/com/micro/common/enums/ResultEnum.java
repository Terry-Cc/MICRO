package com.micro.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: www
 * @description: 结果枚举
 * @author: XiongJiaMin
 * @create: 2021-09-26 15:16
 **/
@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * code 10199 msg 空响应
     */
    COMMON_UNKNOWN_RESP("10199", "空响应"),

    /**
     * code 10200 msg 请求成功
     */
    COMMON_SUCCESS_RESP("10200", "请求成功"),

    /**
     * code 10201 msg 请求失败
     */
    COMMON_ERROR_RESP("10201", "请求失败"),

    /**
     * code 10202 msg 服务端异常
     */
    COMMON_SERVICE_EXP("10202", "服务端异常"),

    /**
     * code 10203 msg 数据转换异常
     */
    COMMON_PARSE_EXP("10203", "数据转换异常"),

    /**
     * code 10204 msg 参数解析异常
     */
    COMMON_INSPECTED_EXP("10204", "参数解析异常"),

    /**
     * code 10404 msg 服务未找到
     */
    COMMON_NOTFOUND_EXP("10404", "服务未找到"),

    /**
     * code 10404 msg 未知异常
     */
    COMMON_UNKNOWN_EXP("10404", "未知异常"),

    /**
     * code 10500 msg 服务请求超时
     */
    COMMON_TIMEOUT_EXP("10500", "服务请求超时");

    /**
     * @description 结果代码
     * @create: 2021/9/26
     */
    private final String rspCode;

    /**
     * @description 结果信息
     * @create: 2021/9/26
     */
    private final String rspMsg;
}
