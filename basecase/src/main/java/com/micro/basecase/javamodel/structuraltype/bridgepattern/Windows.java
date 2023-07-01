package com.micro.basecase.javamodel.structuraltype.bridgepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  Windows操作系统
 * </p>
 * @since 2023/7/1 16:53
 */
public class Windows extends OperatingSystem {

    public Windows(Video video) {
        super(video);
    }

    @Override
    public void play(String fileName) {
        System.out.print("Windows操作系统");
        this.video.decode(fileName);
    }
}
