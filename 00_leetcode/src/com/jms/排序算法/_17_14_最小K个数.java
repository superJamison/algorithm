package com.jms.排序算法;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/11 20:37
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 */
public class _17_14_最小K个数 {
    public static void main(String[] args) {
        int[] ints = {22, 3, 4, 77, 1, 3, 4, 2, 7, 4, 6, 1, 4, 6};
        int[] result = smallestK(ints, 4);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }

    public static int[] smallestK(int[] arr, int k) {
        int n = arr.length;
        return quick_sort(arr, 0, n - 1, k);
    }

    public static int[] quick_sort(int[] a, int l, int r,  int k){
        if(l >= r) return new int[]{};
        int i = l - 1, j = r + 1;
        int x = a[l];

        while(i < j)
        {
            do i ++; while( x > a[i]);
            do j --; while( x < a[j]);
            if(i < j)
            {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        if(j + 1 < k) quick_sort(a, j + 1, r, k);
        if(j + 1 > k) quick_sort(a, l, j, k);
        return Arrays.copyOf(a, k);
    }


    public static int[] smallestK2(int[] arr, int k) {
        int[] result = new int[k];

        if (k <= 0) return result;
        if (arr.length <= k) return arr;

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()){
                queue.poll();
                queue.add(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }

        return result;
    }

    public static int[] smallestK1(int[] arr, int k) {
        int[] result = new int[k];
        if (arr.length <= k){
            return arr;
        }

        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }

        return result;
    }
}
