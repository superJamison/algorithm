package com.jms.sort.noCmp;

import com.jms.sort.Sort;
import com.jms.tools.Integers;

/**
 * 基数排序  O(d * (n + k)) d是最大值位数   k是进制
 * @author Jamison
 * @version 1.0
 * @date 2021/4/7 21:57
 */
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //找出最大值
        int max = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
        }

        for (int divider = 1; divider <= max ; divider *= 10) {
            countingSort(divider);
        }
        Integers.println(arrays);
    }

    private void countingSort(int divider){
        //计算次数
        int[] counts = new int[10];
        for (int i = 0; i < arrays.length; i++) {
            counts[arrays[i] / divider % 10]++;
        }

        //counts累加
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        //从后往前遍历，将他放在有序数组的合适位置
        int[] newArrays = new int[arrays.length];
        for (int i = arrays.length - 1; i >= 0; i--) {
            newArrays[--counts[arrays[i] / divider % 10]] = arrays[i];
        }

        //将有序数组复制到array
        for (int i = 0; i < newArrays.length; i++) {
            arrays[i] = newArrays[i];
        }
    }
}
