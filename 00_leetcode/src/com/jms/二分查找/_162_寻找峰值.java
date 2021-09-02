package com.jms.二分查找;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/1 22:38
 */
public class _162_寻找峰值 {
    public static void main(String[] args) {
        int result = findPeakElement(new int[]{1, 2, 3, 1});
        System.out.println(result);
    }

    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        while (left < right){
            int mid = left + ((right - left) >> 1);

            // mid处于下坡路段
            if (nums[mid] > nums[mid + 1]){
                right = mid;
            }
            // mid处于上坡路段
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int findPeakElement1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }

        return nums.length - 1;
    }
}
