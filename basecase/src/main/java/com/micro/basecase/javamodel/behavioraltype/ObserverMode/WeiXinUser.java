package com.micro.basecase.javamodel.behavioraltype.ObserverMode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  微信用户
 * </p>
 * @since 2023/7/2 13:01
 */
@AllArgsConstructor
public class WeiXinUser implements Observer {

    private String name;

    @Override
    public void update(String message) {
        System.out.println(name + "已经接受到推送:" + message);
    }
}
