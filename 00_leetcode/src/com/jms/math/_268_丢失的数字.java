package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 20:11
 */
public class _268_丢失的数字 {

    public int missingNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        for (int i = 1; i < nums.length; i++) {
            result ^= i;
        }
        return result;
    }

    public int missingNumber1(int[] nums) {
        int n = nums.length;
        int result = n * (n + 1) / 2;
        for (int i = 0; i < nums.length; i++) {
            result -= nums[i];
        }
        return result;
    }
}
