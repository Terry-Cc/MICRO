package com.micro.common.util.sort;

import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  希尔排序
 * </p>
 * @since 2023/5/23 21:54
 */
public final class ShellSort<E extends Comparable<E>> implements SortAlgorithm<E> {

    static <E extends Comparable<E>> ShellSort<E> instance() {
        return new ShellSort<>();
    }

    @Override
    public void sort(List<E> sortElements) {
        int gap = sortElements.size() / 2;
        while (gap > 0) {
            for (int last = gap; last < sortElements.size(); last += gap) {
                int start = last;
                E temp = sortElements.get(last);
                while (start - gap >= 0 && sortElements.get(start - gap).compareTo(temp) > 0) {
                    sortElements.set(start, sortElements.get(start - gap));
                    start -= gap;
                }
                sortElements.set(start, temp);
            }
            gap /= 2;
        }
    }
}
