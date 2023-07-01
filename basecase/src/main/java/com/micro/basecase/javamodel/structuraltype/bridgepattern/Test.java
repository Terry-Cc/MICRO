package com.micro.basecase.javamodel.structuraltype.bridgepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  桥接模式测试类
 * </p>
 * @since 2023/7/1 16:55
 */
public class Test {

    public static void main(String[] args){
        Mkv mkv = new Mkv();
        Mp4 mp4 = new Mp4();
        Windows windows = new Windows(mkv);
        Android android = new Android(mp4);
        windows.play("奇异博士");
        android.play("绝命毒师");
    }
}
