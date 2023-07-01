package com.micro.basecase.javamodel.creationtype.builder;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  电脑框架
 * </p>
 * @since 2023/7/1 14:50
 */
@Data
@NoArgsConstructor
public class Computer {

    /**
     * @since 2023/6/30 19:30
     * @description <p>
     *  主机
     * </p>
     */
    private String motherboard;

    /**
     * @since 2023/6/30 19:30
     * @description <p>
     *  处理器
     * </p>
     */
    private String cpu;

    /**
     * @since 2023/6/30 19:30
     * @description <p>
     *  内存
     * </p>
     */
    private String memory;

    /**
     * @since 2023/6/30 19:31
     * @description <p>
     *  磁盘
     * </p>
     */
    private String disk;

    /**
     * @since 2023/6/30 19:31
     * @description <p>
     *  图形处理器
     * </p>
     */
    private String gpu;

    /**
     * @since 2023/6/30 19:32
     * @description <p>
     *  电源
     * </p>
     */
    private String power;

    /**
     * @since 2023/6/30 19:32
     * @description <p>
     *  散热器
     * </p>
     */
    private String heatSink;

    /**
     * @since 2023/6/30 19:36
     * @description <p>
     *  底座
     * </p>
     */
    private String chassis;
}