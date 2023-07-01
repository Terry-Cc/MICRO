package com.micro.basecase.javamodel.structuraltype.compositepattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  子菜单
 * </p>
 * @since 2023/7/1 17:45
 */
public class MenuItem extends MenuComponent {

    public MenuItem(String name, int level) {
        super(name, level);
    }

    @Override
    public void show() {
        for (int i = 0; i < this.level; i++) {
            System.out.print("---");
        }
        System.out.println(this.name);
    }
}
