package com.jms.排序算法;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/27 10:01
 */
public class _5_快速排序 {

    private static int[] a;

    public static void main(String[] args) {
//        a = new int[]{20, 40, 30, 10, 60, 50};
//        a = new int[]{10, 20, 30, 40, 50, 60};
        a = new int[]{3, 12, 1, 33, 5, 6, 7, 2, 0};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
        quickSort(0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }

    private static void quickSort(int start, int end) {
        // 选定第一个作为基准
        if (start < end) {

            int left = start;
            int right = end;
            int benchmark = a[left];

            while (left < right) {
                // 从右往左找到小于benchmark的数
                while (left < right && benchmark < a[right]) {
                    right--;
                }
                if (left < right) {
                    a[left++] = a[right];
                }
                // 从左往右找到大于benchmark的数
                while (left < right && benchmark > a[left]) {
                    left++;
                }
                if (left < right) {
                    a[right--] = a[left];
                }
            }
            a[left] = benchmark;

            quickSort(start, left - 1);
            quickSort(left + 1, end);
        }
    }
}
