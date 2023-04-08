package com.micro.common.util.sort;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 数据处理流
 * @since 2023-03-02 17:40
 **/
public interface IDataStream<E> {

    IDataStream<E> sort(SortFunction<List<E>> sorter);

    IDataStream<E> add(List<E> elements);

    IDataStream<E> set(List<E> elements);

    List<E> get();
}
