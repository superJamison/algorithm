package com.jms.sort;

/**
 * 归并排序
 * 复杂度：nlog(n)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/4 17:05
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[arrays.length >> 1];
        sort(0, arrays.length);
    }

    /**
     * 对 [begin, end)位置的元素进行归并排序
     */
    private void sort(int begin, int end){
        if (end - begin < 2) return;

        int middle = (begin + end) >> 1;
        sort(begin, middle);
        sort(middle, end);
        merge(begin, middle, end);
    }

    /**
     * 将 [begin, middle)和[middle, end)的两个有序序列合并为一个有序的数组
     */
    private void merge(int begin, int middle, int end) {
        int li = 0, le = middle - begin,
                ri = middle, re = end,
                ai = begin;

        for (int i = li; i < le; i++) {
            leftArray[i] = arrays[begin + i];
        }

        while (li < le){
            if (ri < re && cmpElement(arrays[ri], leftArray[li]) < 0){
                arrays[ai++] = arrays[ri++];
            }else {
                arrays[ai++] = leftArray[li++];
            }
        }

    }
}
