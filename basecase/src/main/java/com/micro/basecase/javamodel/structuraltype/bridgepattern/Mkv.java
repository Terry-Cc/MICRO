package com.micro.basecase.javamodel.structuraltype.bridgepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  Mkv格式
 * </p>
 * @since 2023/7/1 16:47
 */
public class Mkv implements Video {

    @Override
    public void decode(String fileName) {
        System.out.println("开始播放Mkv格式视频:" + fileName);
    }
}
