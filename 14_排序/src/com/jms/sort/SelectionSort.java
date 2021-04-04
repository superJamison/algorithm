package com.jms.sort;

/**
 * 选择 排序   是稳定的排序  平均复杂度：O(nlogn)  空间：O(1)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 20:51
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T>{

    @Override
    protected void sort() {
        for (int end = arrays.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int bedin = 1; bedin <= end; bedin++) {
                if (cmp(maxIndex, bedin) <= 0){
                    maxIndex = bedin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
