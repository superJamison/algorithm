package com.jms.排序算法;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 20:53
 */
public class _1_冒泡排序 {

    public static void main(String[] args) {
        int[] a = {20,40,30,10,60,50};
//        int[] a = {10, 20, 30, 40, 50, 60};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
        int[] result = bubbleSort1(a, a.length);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ", ");
        }
    }

    /**
     * （改进版）
     * @param a 要排序的数组
     * @param n 数组的长度
     * @return 返回排序好的数组
     */
    public static int[] bubbleSort(int[] a, int n){
        // 遍历n-1次
        int temp = 0;
        // 设置一个标记
        boolean flag;

        for (int i = n - 1; i > 0; i--) {
            System.out.println("for 1");
            flag = true;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]){
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = false;
                }
            }
            // 若flag为true则表明数组已经是排好序的，直接终止循环
            if (flag){
                break;
            }
        }

        return a;
    }

    /**
     *
     * @param a 要排序的数组
     * @param n 数组的长度
     * @return 返回排序好的数组
     */
    public static int[] bubbleSort1(int[] a, int n){
        // 遍历n-1次
        int temp = 0;
        for (int i = n - 1; i > 0; i--) {
            System.out.println("for 1");
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]){
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }
}
