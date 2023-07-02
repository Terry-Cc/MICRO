package com.micro.basecase.javamodel.behavioraltype.mementopattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  备忘录模式测试类
 * </p>
 * @since 2023/7/2 16:07
 */
public class Test {

    public static void main(String[] args){
        Role role = new Role();
        // 开始游戏
        role.init();
        role.showState();
        // 备份状态
        Memento memento = role.creatBacker();
        RoleStateBackUp roleStateBackUp = new RoleStateBackUp();
        roleStateBackUp.setMemento(memento);
        // 开始战斗
        role.fight();
        role.showState();
        // 战斗后恢复状态
        role.returnState(roleStateBackUp.getMemento());
        role.showState();
    }
}
