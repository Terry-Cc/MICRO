package com.micro.basecase.javamodel.creationtype.prototype;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  原型模式测试类
 * </p>
 * @since 2023/6/30 19:19
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Factory aFactory = new Factory(100, "科技公司");
        // 通过科技公司为原型新创建一个同样人数的保险公司
        Factory bFactory = aFactory.clone();
        bFactory.setName("保险公司");
        System.out.println(aFactory.toString());
        System.out.println(bFactory.toString());
    }
}
