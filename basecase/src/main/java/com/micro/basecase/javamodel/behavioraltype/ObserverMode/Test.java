package com.micro.basecase.javamodel.behavioraltype.ObserverMode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  观察者模式测试类
 * </p>
 * @since 2023/7/2 13:09
 */
public class Test {

    public static void main(String[] args){
        Wananchi wananchi = new Wananchi();
        WeiXinUser zhangSan = new WeiXinUser("张三");
        WeiXinUser liSi = new WeiXinUser("李四");
        wananchi.register(zhangSan);
        wananchi.register(liSi);
        wananchi.notify("周杰伦演唱会门票开始预售啦!");
    }
}
