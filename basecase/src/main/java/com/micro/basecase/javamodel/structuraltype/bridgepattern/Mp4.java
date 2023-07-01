package com.micro.basecase.javamodel.structuraltype.bridgepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  Mp4格式
 * </p>
 * @since 2023/7/1 16:46
 */
public class Mp4 implements Video {

    @Override
    public void decode(String fileName) {
        System.out.println("开始播放Mp4格式视频:" + fileName);
    }
}
