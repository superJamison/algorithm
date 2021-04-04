package com.jms.sort;

import com.jms.tools.Asserts;

/**
 * 折半查找
 * @author Jamison
 * @version 1.0
 * @date 2021/4/4 15:49
 */
public class BinarySearch {

    /**
     * 折半查找
     * @param array 数组
     * @param v 要搜索的元素
     * @return 返回元素v在数组中的位置
     */
    private static int indexOf(int[] array, int v){
        if (array == null || array.length == 0){
            return -1;
        }

        //[begin, end) 左闭右开区间
        int begin = 0;
        int end = array.length;

        while (begin < end){
            int middle = (begin + end) >> 1;
            if (v < array[middle]){
                end = middle;
            }else if (v > array[middle]){
                begin = middle + 1;
            }else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 查找v在有序数组array中的待插入位置
     */
    private static int search(int[] array, int v){
        if (array == null || array.length == 0) return -1;

        //[begin, end) 左闭右开区间
        int begin = 0;
        int end = array.length;

        while (begin < end){
            int middle = (begin + end) >> 1;
            if (v < array[middle]){
                end = middle;
            }else {
                begin = middle + 1;
            }
        }
        return begin;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{2,4,6,8,10};
//
//        Asserts.test(indexOf(array, 4) == 1);
//        Asserts.test(indexOf(array, 2) == 0);
//        Asserts.test(indexOf(array, 10) == 4);
//        Asserts.test(indexOf(array, 12) == -1);

        int[] array = new int[]{2,4,8,8,8,12,14};

        Asserts.test(search(array, 5) == 2);
        Asserts.test(search(array, 1) == 0);
        Asserts.test(search(array, 15) == 7);
        Asserts.test(search(array, 8) == 5);
    }
}
