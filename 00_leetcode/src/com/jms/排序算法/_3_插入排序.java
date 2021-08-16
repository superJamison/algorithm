package com.jms.排序算法;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 22:43
 */
public class _3_插入排序 {
    public static void main(String[] args) {
        int[] a = {20,40,30,10,60,50};
//        int[] a = {10, 20, 30, 40, 50, 60};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
        int[] result = insertSort(a, a.length);
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
    public static int[] insertSort(int[] a, int n){
        int index = 0;
        int temp = 0;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (a[j + 1] < a[j]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }else{
                    break;
                }
            }
        }
        return a;
    }
}
