package com.micro.common.util.sort;

import java.util.ArrayList;

/**
 * @author XiongJiaMin
 * @apiNote 排序工厂
 * @since 2023-03-02 10:06
 **/
@SuppressWarnings("unused")
public final class SortFactory {


    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(4);
        integers.add(1);
        integers.add(6);
        integers.add(9);

        DataStream.of(integers).sort(BubbleSort.<Integer>INSTANCE()::sort);

        System.out.println(integers);
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.sort(integers);
        System.out.println(integers);


    }

}
