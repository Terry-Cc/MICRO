package com.micro.common.util.sort;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 排序算法
 * @since 2023-02-28 09:47
 **/
public interface SortAlgorithm<E extends Comparable<E>> {

    void sort(List<E> sortElements);
}
