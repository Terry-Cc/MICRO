package com.micro.common.util.seek;

import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  查找算法
 * </p>
 * @since 2023/6/14 17:20
 */
public interface SeekAlgorithm<E extends Comparable<E>> {

    /**
     * @since 2023/6/14 17:27
     * @description <p>
     *  查找, 存在返回1,不存在返回-1
     * </p>
     * @param elements 被查找的元素集合
     * @param seekElement 查找的元素
     * @return int 存在 1 不存在 -1
     */
    int seek(List<E> elements, E seekElement);
}
