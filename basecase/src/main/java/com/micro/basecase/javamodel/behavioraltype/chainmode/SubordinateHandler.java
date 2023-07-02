package com.micro.basecase.javamodel.behavioraltype.chainmode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  下级领导
 * </p>
 * @since 2023/7/2 11:52
 */
public class SubordinateHandler extends Handler {

    @Override
    public void handler() {
        System.out.print("下级领导->");
    }
}
