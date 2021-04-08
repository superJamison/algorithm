package com.jms.sort.cmp;

import com.jms.sort.Sort;

import java.util.Map;

/**
 * 快速排序，复杂度:   最好nlog(n)  最坏：n^2  不稳定排序
 * @author Jamison
 * @version 1.0
 * @date 2021/4/4 19:27
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, arrays.length);
    }

    private void sort(int begin, int end){
        if ((end - begin) < 2) return;

        int middle = pivotIndex(begin, end);
        sort(begin, middle);
        sort(middle + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        //随机选择一个元素跟begin进行交换
        swap(begin, begin + (int)(Math.random() * (end - begin)));

        //备份轴点元素
        T v = arrays[begin];
        end--;

        while (begin < end){
            while (begin < end){
                if (cmpElement(v, arrays[end]) < 0){
                    end--;
                }else {
                    arrays[begin++] = arrays[end];
                    break;
                }
            }
            while (begin < end){
                if (cmpElement(v, arrays[begin]) > 0){
                    begin++;
                }else {
                    arrays[end--] = arrays[begin];
                    break;
                }
            }
        }

        arrays[begin] = v;
        return begin;
    }


}
