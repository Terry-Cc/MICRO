package com.micro.basecase.javamodel.structuraltype.bridgepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  Rmvb格式
 * </p>
 * @since 2023/7/1 16:50
 */
public class Rmvb implements Video {

    @Override
    public void decode(String fileName) {
        System.out.println("开始播放Rmvb格式视频:" + fileName);
    }
}
