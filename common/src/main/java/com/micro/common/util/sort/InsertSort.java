package com.micro.common.util.sort;

import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  直接插入排序
 * </p>
 * @since 2023/5/23 21:40
 */
public final class InsertSort<E extends Comparable<E>> implements SortAlgorithm<E> {

    static <E extends Comparable<E>> InsertSort<E> instance() {
        return new InsertSort<>();
    }

    @Override
    public void sort(List<E> sortElements) {
        for (int first = 1; first < sortElements.size(); first++) {
            E temp = sortElements.get(first);
            int preFirst = first - 1;
            for (; preFirst >= 0; preFirst--) {
                if (sortElements.get(preFirst).compareTo(temp) > 0) {
                    sortElements.set(preFirst + 1, sortElements.get(preFirst));
                } else {
                    break;
                }
            }
            sortElements.set(preFirst + 1, temp);
        }
    }
}
