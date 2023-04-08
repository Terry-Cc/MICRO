package com.micro.common.util.sort;

import com.alibaba.fastjson.JSON;
import com.micro.common.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 冒泡排序
 * @since 2023-02-28 09:54
 **/
public final class BubbleSort<E extends Comparable<E>> implements SortAlgorithm<E> {

    public static <E extends Comparable<E>> BubbleSort<E> INSTANCE () {
        return new BubbleSort<>();
    }

    @Override
    public void sort(List<E> sortElements) {
        //冒泡
        for (int i = 0; i < sortElements.size(); i++) {
            //外层循环，遍历次数
            for (int j = 0; j < sortElements.size() - i - 1; j++) {
                //内层循环，升序（如果前一个值比后一个值大，则交换）
                //内层循环一次，获取一个最大值
                if (sortElements.get(j).compareTo(sortElements.get(j + 1)) > 0) {
                    E temp = sortElements.get(j + 1);
                    sortElements.set(j + 1, sortElements.get(j));
                    sortElements.set(j, temp);
                }
            }
        }
    }
}
