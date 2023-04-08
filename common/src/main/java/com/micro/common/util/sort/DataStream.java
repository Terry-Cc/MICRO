package com.micro.common.util.sort;

import java.util.List;
import java.util.Objects;

/**
 * @author XiongJiaMin
 * @apiNote 数据处理流
 * @since 2023-03-02 17:59
 **/
public class DataStream<E> implements IDataStream<E> {

    private List<E> elements;

    @Override
    public IDataStream<E> sort(SortFunction<List<E>> sorter) {
        Objects.requireNonNull(sorter);
        Objects.requireNonNull(this.elements);
        sorter.handle(this.elements);
        return this;
    }

    @Override
    public IDataStream<E> add(List<E> elements) {
        Objects.requireNonNull(elements);
        this.elements.addAll(elements);
        return this;
    }

    @Override
    public IDataStream<E> set(List<E> elements) {
        Objects.requireNonNull(elements);
        this.elements = elements;
        return this;
    }

    public static<E> IDataStream<E> of(List<E> elements) {
        Objects.requireNonNull(elements);
        DataStream<E> dataStream = new DataStream<>();
        dataStream.elements = elements;
        return dataStream;
    }


    @Override
    public List<E> get() {
        return this.elements;
    }
}
