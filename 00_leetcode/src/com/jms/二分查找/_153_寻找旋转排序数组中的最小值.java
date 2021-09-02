package com.jms.二分查找;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/1 20:27
 */
public class _153_寻找旋转排序数组中的最小值 {

    public static void main(String[] args) {
        int result = findMin(new int[]{3, 4, 5, 1, 2});
//        int result = findMin(new int[]{1});
        System.out.println(result);
    }

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;

        int left = 0, right = nums.length - 1;

        while (left < right){
            int mid = left + ((right - left) >> 1);

            if (nums[mid] < nums[right]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
