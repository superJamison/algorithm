package com.jms.sort.cmp;

import com.jms.sort.Sort;

/**
 * 气泡排序/冒泡排序   是稳定的排序  平均复杂度：O(nlogn)  空间：O(1)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 20:51
 */
public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int end = arrays.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
