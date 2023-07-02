package com.micro.basecase.javamodel.behavioraltype.mediatorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  中介者模式测试类
 * </p>
 * @since 2023/7/2 13:30
 */
public class Test {

    public static void main(String[] args){
        MediatorCompany mediatorCompany = new MediatorCompany();
        HouseOwner wangJie = new HouseOwner("王姐", mediatorCompany);
        Tenant xiaoLi = new Tenant("小李", mediatorCompany);
        mediatorCompany.setHouseOwner(wangJie);
        mediatorCompany.setTenant(xiaoLi);
        wangJie.connect(xiaoLi.getName() + "你好,我这有房屋可以出租,便宜!");
        xiaoLi.connect(wangJie.getName() + "你好,周末可以看看房子么?");
    }
}
