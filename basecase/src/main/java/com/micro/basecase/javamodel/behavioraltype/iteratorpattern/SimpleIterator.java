package com.micro.basecase.javamodel.behavioraltype.iteratorpattern;

import lombok.AllArgsConstructor;
import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  迭代器实现
 * </p>
 * @since 2023/7/2 13:47
 */
@AllArgsConstructor
public class SimpleIterator<T> implements Iterator<T> {

    private int point;

    private List<T> elements;

    @Override
    public boolean hasNext() {
        return point < elements.size();
    }

    @Override
    public T next() {
        if (hasNext()) {
            return elements.get(point++);
        }
        return null;
    }
}
