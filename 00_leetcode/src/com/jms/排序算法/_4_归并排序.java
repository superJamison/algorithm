package com.jms.排序算法;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 23:07
 */
public class _4_归并排序 {

    private static int[] a;

    public static void main(String[] args) {
//        a = new int[]{20,40,30,10,60,50};
//        a = new int[]{10, 20, 30, 40, 50, 60};
        a = new int[]{3, 12, 1, 33, 5, 6, 7, 2, 0};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
        mergeSort(a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }

    public static void mergeSort(int n){
        sort(0, n);
    }

    // 对[start, end)区间进行归并排序
    public static void sort(int start, int end){
        if (end - start < 2){
            return;
        }

        int mid = (start + end) >> 1;
        sort(start, mid);
        sort(mid, end);
        merge(start, mid, end);
    }

    private static void merge(int start, int mid, int end) {
        // 将mid左边的数据备份出来
        int leftArrayLength = mid - start;
        int[] leftArray = new int[leftArrayLength];

        for (int i = start; i < start + leftArrayLength; i++) {
            leftArray[i - start] = a[i];
        }

        int li = 0, ri = mid, index = start;
        while (li < leftArrayLength){
            // 如果ri>end了就将leftArray剩余的数据赋值到a上
            if (ri >= end) {
                a[index] = leftArray[li++];
                index++;
                continue;
            }

            if (leftArray[li] > a[ri]){
                a[index] = a[ri++];
            }else {
                a[index] = leftArray[li++];
            }
            index++;
        }
    }
}
