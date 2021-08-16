package com.jms.排序算法;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 21:22
 */
public class _2_选择排序 {
    public static void main(String[] args) {
        int[] a = {20,40,30,10,60,50};
//        int[] a = {10, 20, 30, 40, 50, 60};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
        int[] result = selectSort(a, a.length);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ", ");
        }
    }

    /**
     *
     * @param a 要排序的数组
     * @param n 数组的长度
     * @return 返回排序好的数组
     */
    public static int[] selectSort(int[] a, int n){
        int index = 0;
        // 存放未排序区的最小值
        int temp;

        for (int i = 0; i < n - 1; i++) {
            temp = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (temp > a[j]){
                    temp = a[j];
                    index = j;
                }
            }
            a[index] = a[i];
            a[i] = temp;
        }

        return a;
    }
}
