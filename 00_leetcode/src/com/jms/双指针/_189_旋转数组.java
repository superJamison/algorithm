package com.jms.双指针;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/17 17:22
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * 题目：
 *      给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 示例：
 *      输入: nums = [1,2,3,4,5,6,7], k = 3
 *      输出: [5,6,7,1,2,3,4]
 */
public class _189_旋转数组 {

    public static void main(String[] args) {
        rotate(new int[]{1,2,3,4,5,6,7}, 3);

        // 4,3,2,1,7,6,5
        // 5,6,7,1,2,3,4
    }

    /**
     * 直接遍历，除暴的方法
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;

        if (k == 0) {
           return;
        }

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int l, int r) {
        while (l < r){
            int temp = nums[r];
            nums[r--] = nums[l];
            nums[l++] = temp;
        }
    }
}
