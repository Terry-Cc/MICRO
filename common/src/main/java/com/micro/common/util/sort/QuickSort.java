package com.micro.common.util.sort;

import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  快速排序
 * </p>
 * @since 2023/5/23 22:41
 */
public final class QuickSort<E extends Comparable<E>> implements SortAlgorithm<E> {

    static <E extends Comparable<E>> QuickSort<E> instance() {
        return new QuickSort<>();
    }

    @Override
    public void sort(List<E> sortElements) {
        quick(sortElements, 0, sortElements.size() - 1);
    }

    private void quick(List<E> sortElements, int start, int end) {
        //当开始位置小于结束位置时（数组有数据）  进行排序  也就是递归入口
        if (start < end) {
            //首先找到基准数  作为比较的标准数  取数组开始位置   从哪里开始  用哪个数当标准数 这个就是标准数
            E stard = sortElements.get(start);
            //记录需要进行排序的下标
            int low = start;
            int high = end;
            //循环比对比标准数大和小的数字
            while (low < high) {
                //如果标准数小于右边的数字  把右边的游标卡尺向左移动
                while (low < high && stard.compareTo(sortElements.get(high)) <= 0) {
                    high--;
                }
                //如果标准数大于 右边的数字
                //用低位数字替换右边数字
                sortElements.set(low, sortElements.get(high));
                //如果左边的数字比标准数小
                while (low < high && sortElements.get(low).compareTo(stard) <= 0) {
                    low++;
                }
                //如果左边的数字比标准数大
                //用左边数字替换右边数字
                sortElements.set(high, sortElements.get(low));
            }
            //把标准数赋给高或者低所在的元素
            sortElements.set(low, stard);
            //处理所有比标准数小的数字
            quick(sortElements, start, low);
            //处理所有比标准数大的数字
            quick(sortElements, low + 1, end);
        }
    }
}
