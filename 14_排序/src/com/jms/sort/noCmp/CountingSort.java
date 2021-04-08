package com.jms.sort.noCmp;

import com.jms.sort.Sort;
import com.jms.tools.Integers;

import java.util.Arrays;

/**
 * 计数排序 O(n + k)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/7 20:58
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //求出最值
        int max = arrays[0];
        int min = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
            if (arrays[i] < min){
                min = arrays[i];
            }
        }

        //计算次数
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < arrays.length; i++) {
            counts[arrays[i] - min]++;
        }

        //counts累加
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        //从后往前遍历，将他放在有序数组的合适位置
        int[] newArrays = new int[arrays.length];
        for (int i = arrays.length - 1; i >= 0; i--) {
            newArrays[--counts[arrays[i] - min]] = arrays[i];
        }

        //将有序数组复制到array
        for (int i = 0; i < newArrays.length; i++) {
            arrays[i] = newArrays[i];
        }
        Integers.println(arrays);
    }

    private void sort0(){
        int max = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
        }

        //开辟一个max+1内存空间的数组
        int[] counts = new int[max + 1];
        for (int i = 0; i < arrays.length; i++) {
            counts[arrays[i]]++;
        }

        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0){
                arrays[index++] = i;
            }
        }
        Integers.println(arrays);
    }
}
