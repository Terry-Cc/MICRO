package com.micro.basecase.javamodel.structuraltype.compositepattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  菜单组件
 * </p>
 * @since 2023/7/1 17:34
 */
@AllArgsConstructor
public abstract class MenuComponent {

    String name;

    int level;

    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException("不支持添加操作!");
    }

    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException("不支持移除操作!");
    }

    public abstract void show();
}
