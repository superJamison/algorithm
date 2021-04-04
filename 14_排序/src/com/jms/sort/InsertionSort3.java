package com.jms.sort;

/**
 * 插入排序：最坏、平均时间复杂度是O(n^2)  最好时间复杂度是：O(n)  稳定性：稳定   空间复杂度：O(1)
 *
 * 特点：逆序对越多，时间复杂度越高
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 22:29
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < arrays.length; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 将begin位置的元素插入到dest位置上
     */
    private void insert(int begin, int dest) {
        T v = arrays[begin];
        for (int i = begin; i > dest; i--) {
            arrays[begin] = arrays[begin - 1];
        }
        arrays[dest] = v;
    }

    /**
     * 查找index位置的元素在数组中的待插入的位置
     */
    private int search(int index){
        T v = arrays[index];

        //[begin, end) 左闭右开区间
        int begin = 0;
        int end = index;
        while (begin < end){
            int middle = (begin + end) >> 1;
            if (cmpElement(v, arrays[middle]) < 0){
                end = middle;
            }else {
                begin = middle + 1;
            }
        }
        return begin;
    }
}
