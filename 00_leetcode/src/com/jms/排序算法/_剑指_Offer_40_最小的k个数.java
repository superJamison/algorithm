package com.jms.排序算法;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/14 16:13
 */
public class _剑指_Offer_40_最小的k个数 {

    public static void main(String[] args) {

    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) return new int[]{};

        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] arr, int l, int r, int k) {
        int index = getIndex(arr, l, r);
        // 返回的位置刚好等于k，则直接返回k以及左边的所有元素。
        if (index == k) return Arrays.copyOf(arr, k + 1);

        return index > k ? quickSearch(arr,l,index - 1, k) : quickSearch(arr,index + 1, r, k);
    }

    private int getIndex(int[] arr, int low, int high) {
        int temp = arr[low];

        while (low < high){
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= temp) high--;
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && arr[low] <= temp) low++;
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high] = arr[low];
        }

        arr[low] = temp;
        return low;
    }

    public int[] getLeastNumbers1(int[] arr, int k) {
        if (arr == null || arr.length == 0) return new int[]{};

        Arrays.sort(arr);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }

        return result;
    }
}
