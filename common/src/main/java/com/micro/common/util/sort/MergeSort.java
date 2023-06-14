package com.micro.common.util.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  归并排序
 * </p>
 * @since 2023/5/23 22:51
 */
public class MergeSort<E extends Comparable<E>> implements SortAlgorithm<E> {

    static <E extends Comparable<E>> MergeSort<E> instance() {
        return new MergeSort<>();
    }

    @Override
    public void sort(List<E> sortElements) {
        mangetout(sortElements, 0, sortElements.size() - 1);
    }

    private void mangetout(List<E> sortElements, int i, int j) {
        int mid;
        if (i < j) {
            mid = (i + j) / 2;
            mangetout(sortElements, i, mid);
            mangetout(sortElements, mid + 1, j);
            merge(sortElements, i, mid, j);
        }
    }

    private void merge(List<E> sortElements, int i, int mid, int j) {
        List<E> temp = new ArrayList<>(sortElements);
        int l = i;
        int m = mid + 1;
        int k = l;
        while (l <= mid && m <= j) {
            if (sortElements.get(l).compareTo(sortElements.get(m)) < 1) {
                temp.set(k++, sortElements.get(l++));
            } else {
                temp.set(k++, sortElements.get(m++));
            }
        }
        while (l <= mid) {
            temp.set(k++, sortElements.get(l++));
        }
        while (m <= j) {
            temp.set(k++, sortElements.get(m++));
        }
        for (int n = i; n <= j; n++) {
            sortElements.set(n, temp.get(n));
        }
    }
}
