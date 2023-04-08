package com.micro.common.util.sort;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 排序方法
 * @since 2023-03-03 11:47
 **/
@FunctionalInterface
public interface SortFunction<T> {

    void handle(T T);
}
