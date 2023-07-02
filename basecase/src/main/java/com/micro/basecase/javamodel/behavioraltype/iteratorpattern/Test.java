package com.micro.basecase.javamodel.behavioraltype.iteratorpattern;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  迭代器模式测试类
 * </p>
 * @since 2023/7/2 14:08
 */
public class Test {

    public static void main(String[] args){
        MyList<User> myList = new MyArrayList<>();
        myList.add(new User("张三"));
        myList.add(new User("李四"));
        myList.add(new User("王五"));
        Iterator<User> iterator = myList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
