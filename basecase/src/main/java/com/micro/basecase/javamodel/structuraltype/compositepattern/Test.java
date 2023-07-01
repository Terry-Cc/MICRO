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
        MenuComponent main = new Menu("系统", 1);
        MenuComponent menu = new Menu("用户管理系统", 2);
        main.add(menu);
        MenuComponent menu1 = new MenuItem("查询用户", 3);
        MenuComponent menu2 = new MenuItem("删除用户", 3);
        MenuComponent menu3 = new MenuItem("修改用户", 3);
        MenuComponent menu4 = new MenuItem("新增用户", 3);
        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);
        menu.add(menu4);
        MenuComponent menu5 = new Menu("权限管理系统", 2);
        main.add(menu5);
        MenuComponent menu6 = new MenuItem("查询权限", 3);
        MenuComponent menu7 = new MenuItem("删除权限", 3);
        MenuComponent menu8 = new MenuItem("修改权限", 3);
        MenuComponent menu9 = new MenuItem("新增权限", 3);
        menu5.add(menu6);
        menu5.add(menu7);
        menu5.add(menu8);
        menu5.add(menu9);
        main.show();
    }
}
