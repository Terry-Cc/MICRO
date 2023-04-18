package com.micro.common.util.sort;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 选择排序
 * @since 2023-03-02 11:03
 **/
public final class SelectionSort<E extends Comparable<E>> implements SortAlgorithm<E> {

    static <E extends Comparable<E>> SelectionSort<E> instance() {
        return new SelectionSort<>();
    }

    @Override
    public void sort(List<E> sortElements) {
        //选择
        for (int i = 0; i < sortElements.size(); i++) {
            //默认第一个是最小的。
            E min = sortElements.get(i);
            //记录最小的下标
            int index = i;
            //通过与后面的数据进行比较得出，最小值和下标
            for (int j = i + 1; j < sortElements.size(); j++) {
                if (min.compareTo(sortElements.get(j)) > 0) {
                    min = sortElements.get(j);
                    index = j;
                }
            }
            //然后将最小值与本次循环的，开始值交换
            E temp = sortElements.get(i);
            sortElements.set(i, min);
            sortElements.set(index, temp);
            //说明：将i前面的数据看成一个排好的队列，i后面的看成一个无序队列。每次只需要找无需的最小值，做替换
        }
    }
}
