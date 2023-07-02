package com.micro.basecase.javamodel.behavioraltype.iteratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  集合接口
 * </p>
 * @since 2023/7/2 14:01
 */
public interface MyList<T> {

    void add(T t);

    T get(int point);

    Iterator<T> iterator();
}
