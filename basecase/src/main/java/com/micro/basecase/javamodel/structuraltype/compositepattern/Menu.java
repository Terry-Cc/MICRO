package com.micro.basecase.javamodel.structuraltype.compositepattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  菜单
 * </p>
 * @since 2023/7/1 17:39
 */
public class Menu extends MenuComponent {

    protected List<MenuComponent> menuComponentList = new ArrayList<>();

    public Menu(String name, int level) {
        super(name, level);
    }

    @Override
    public void add(MenuComponent menuComponent) {
        this.menuComponentList.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        this.menuComponentList.remove(menuComponent);
    }

    @Override
    public void show() {
        for (int i = 0; i < this.level; i++) {
            System.out.print("---");
        }
        System.out.println(this.name);
        for (MenuComponent menuComponent : this.menuComponentList) {
            menuComponent.show();
        }
    }
}
