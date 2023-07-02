package com.micro.basecase.javamodel.behavioraltype.chainmode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  老板
 * </p>
 * @since 2023/7/2 11:47
 */
public class BossHandler extends Handler {

    @Override
    public void handler() {
        System.out.print("责任: 老板->");
    }
}
