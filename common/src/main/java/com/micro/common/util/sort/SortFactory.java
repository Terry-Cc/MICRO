package com.micro.common.util.sort;

import com.micro.common.util.seek.BinarySeek;

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

        DataStream.of(integers).sort(MergeSort.<Integer>instance()::sort);

        System.out.println(integers);
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        BinarySeek<Integer> binarySeek = new BinarySeek<>();
        bubbleSort.sort(integers);
        System.out.println(integers);
        System.out.println(binarySeek.seek(integers, 2));
    }

}
