package com.micro.common.util.seek;

import java.util.List;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  二分查找, 有序查找
 * </p>
 * @since 2023/6/14 17:28
 */
public class BinarySeek<E extends Comparable<E>> implements SeekAlgorithm<E> {

    static <E extends Comparable<E>> BinarySeek<E> instance() {
        return new BinarySeek<>();
    }

    @Override
    public int seek(List<E> elements, E seekElement) {
        return seek(elements, seekElement, 0, elements.size() - 1);
    }

    private int seek(List<E> elements, E seekElement, int minIndex, int maxIndex) {
        if (minIndex > maxIndex) {
            return -1;
        }
        int midIndex = (minIndex + maxIndex) / 2;
        if (seekElement.compareTo(elements.get(midIndex)) == 0) {
            return 1;
        } else if (seekElement.compareTo(elements.get(midIndex)) < 0) {
            return seek(elements, seekElement, minIndex, midIndex - 1);
        } else {
            return seek(elements, seekElement, midIndex + 1, maxIndex);
        }
    }
}
