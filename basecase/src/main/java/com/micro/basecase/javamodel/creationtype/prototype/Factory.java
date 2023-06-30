package com.micro.basecase.javamodel.creationtype.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  工厂
 * </p>
 * @since 2023/6/30 19:17
 */
@AllArgsConstructor
@Data
public class Factory implements Cloneable {

    /**
     * @since 2023/6/30 19:18
     * @description <p>
     *  工厂人数
     * </p>
     */
    private int peoples;

    /**
     * @since 2023/6/30 19:18
     * @description <p>
     *  工厂名字
     * </p>
     */
    private String name;

    @Override
    protected Factory clone() throws CloneNotSupportedException {
        return (Factory) super.clone();
    }

    @Override
    public String toString() {
        return peoples + "人的" + name;
    }
}
