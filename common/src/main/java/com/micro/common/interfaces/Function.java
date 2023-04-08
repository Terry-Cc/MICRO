package com.micro.common.interfaces;

/**
 * @program: www
 * @description: 接口方法, 有反参
 * @author: XiongJiaMin
 * @create: 2021-10-29 17:51
 **/
@FunctionalInterface
public interface Function<T> {

    T handle();
}
