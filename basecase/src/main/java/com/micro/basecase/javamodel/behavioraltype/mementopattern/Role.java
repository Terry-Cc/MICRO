package com.micro.basecase.javamodel.behavioraltype.mementopattern;

import lombok.AllArgsConstructor;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  角色
 * </p>
 * @since 2023/7/2 16:01
 */
public class Role {

    private int hp;

    private int ack;

    private int def;

    public void init() {
        this.hp = 10000;
        this.ack = 500;
        this.def = 300;
    }

    public void fight() {
        this.hp = 2000;
        this.ack = 500;
        this.def = 300;
    }

    public void showState() {
        System.out.println("HP:" + hp + " ack:" + ack + " def:" + def);
    }

    public Memento creatBacker() {
        return new RoleBacker(this.hp, this.ack, this.def);
    }

    public void returnState(Memento memento) {
        RoleBacker roleBacker = (RoleBacker) memento;
        this.hp = roleBacker.hp;
        this.ack = roleBacker.ack;
        this.def = roleBacker.def;
    }

    /**
     * @since 2023/7/2 16:12
     * @description <p>
     *  角色备份基类
     * </p>
     */
    @AllArgsConstructor
    private class RoleBacker implements Memento {

        private int hp;

        private int ack;

        private int def;
    }
}
