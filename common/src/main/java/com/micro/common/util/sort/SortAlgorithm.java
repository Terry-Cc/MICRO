package com.micro.common.util.sort;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 排序算法
 * @since 2023-02-28 09:47
 **/
public interface SortAlgorithm<E extends Comparable<E>> {

    /**
     * @since 2023/4/18 16:36
     * @description <p>
     *  排序方法实现接口
     * </p>
     * @param sortElements 排序的元素
     */
    void sort(List<E> sortElements);
}
