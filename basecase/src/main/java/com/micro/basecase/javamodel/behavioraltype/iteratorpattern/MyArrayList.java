package com.micro.basecase.javamodel.behavioraltype.iteratorpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  集合实现
 * </p>
 * @since 2023/7/2 14:04
 */
public class MyArrayList<T> implements MyList<T> {

    private List<T> list = new ArrayList<>();

    @Override
    public void add(T t) {
        this.list.add(t);
    }

    @Override
    public T get(int point) {
        return this.list.get(point);
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator<>(0, this.list);
    }
}
