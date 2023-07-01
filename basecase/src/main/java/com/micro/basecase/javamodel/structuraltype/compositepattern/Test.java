package com.micro.basecase.javamodel.structuraltype.compositepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  组合模式测试类
 * </p>
 * @since 2023/7/1 17:47
 */
public class Test {

    public static void main(String[] args){
        Menu main = new Menu("系统", 1);
        Menu menu = new Menu("用户管理系统", 2);
        main.add(menu);
        Menu menu1 = new Menu("查询用户", 3);
        Menu menu2 = new Menu("删除用户", 3);
        Menu menu3 = new Menu("修改用户", 3);
        Menu menu4 = new Menu("新增用户", 3);
        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);
        menu.add(menu4);
        Menu menu5 = new Menu("权限管理系统", 2);
        main.add(menu5);
        Menu menu6 = new Menu("查询权限", 3);
        Menu menu7 = new Menu("删除权限", 3);
        Menu menu8 = new Menu("修改权限", 3);
        Menu menu9 = new Menu("新增权限", 3);
        menu5.add(menu6);
        menu5.add(menu7);
        menu5.add(menu8);
        menu5.add(menu9);
        main.show();
    }
}
