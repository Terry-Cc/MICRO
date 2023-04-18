package com.micro.common.util.sort;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 数据处理流
 * @since 2023-03-02 17:40
 **/
public interface IDataStream<E> {

    /**
     * @since 2023/4/18 16:32
     * @description <p>
     *  排序后返回
     * </p>
     * @param sorter 排序方法
     * @return 数据流
     */
    IDataStream<E> sort(SortFunction<List<E>> sorter);

    /**
     * @since 2023/4/18 16:34
     * @description <p>
     *  添加元素后返回
     * </p>
     * @param elements 元素
     * @return 数据流
     */
    IDataStream<E> add(List<E> elements);

    /**
     * @since 2023/4/18 16:34
     * @description <p>
     *  全覆盖之前的数据后返回
     * </p>
     * @param elements 元素
     * @return 数据流
     */
    IDataStream<E> set(List<E> elements);

    /**
     * @since 2023/4/18 16:35
     * @description <p>
     *  获取全部数据
     * </p>
     * @return 数据
     */
    List<E> get();
}
