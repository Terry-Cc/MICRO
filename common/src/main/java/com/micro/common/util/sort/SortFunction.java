package com.micro.common.util.sort;

/**
 * @author XiongJiaMin
 * @apiNote 排序方法
 * @since 2023-03-03 11:47
 **/
@FunctionalInterface
public interface SortFunction<T> {

    /**
     * @since 2023/4/18 16:38
     * @description <p>
     *  排序的方法
     * </p>
     * @param t 排序的元素
     */
    void handle(T t);
}
