package com.micro.basecase.javamodel.behavioraltype.iteratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  迭代器接口
 * </p>
 * @since 2023/7/2 13:46
 */
public interface Iterator<T> {

    boolean hasNext();

    T next();
}
