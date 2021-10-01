package com.jms.数组;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/30 23:30
 */
public class _31_下一个排列 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1};

        nextPermutation(nums);

        for (int num : nums) {
            System.out.print(num);
        }
    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]){
                Arrays.sort(nums, i, nums.length);
                int index = i;
                while (nums[index] <= nums[i - 1]){
                    index++;
                }
                swap2Num(nums, i - 1, index);
                return;
            }
        }

        int l = 0, r = nums.length - 1;
        while (l < r){
            swap2Num(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap2Num(int[] nums, int i, int j) {
        nums[i] = nums[j] ^ nums[i];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

}
