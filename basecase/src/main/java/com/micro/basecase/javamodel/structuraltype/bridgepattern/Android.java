package com.micro.basecase.javamodel.structuraltype.bridgepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *
 * </p>
 * @since 2023/7/1 16:55
 */
public class Android extends OperatingSystem {

    public Android(Video video) {
        super(video);
    }

    @Override
    public void play(String fileName) {
        System.out.print("Android操作系统");
        this.video.decode(fileName);
    }
}
