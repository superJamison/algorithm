package com.jms.sort.cmp;

import com.jms.sort.Sort;

/**
 * 插入排序：最坏、平均时间复杂度是O(n^2)  最好时间复杂度是：O(n)  稳定性：稳定   空间复杂度：O(1)
 *
 * 特点：逆序对越多，时间复杂度越高
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 22:29
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < arrays.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0){
                swap(cur, cur - 1);
                cur--;
            }
        }
    }
}
