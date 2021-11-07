package com.jms.动态规划;

import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/1 15:45
 */
public class _53_最大子序和 {

    public static void main(String[] args) {
        _53_最大子序和 v = new _53_最大子序和();
        System.out.println(v.maxSubArray(new int[]{-1,-2}));
    }

    public int maxSubArray(int[] nums) {
        int prev = nums[0];
        int max = prev;

        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(nums[i], prev + nums[i]);
            max = Math.max(max, prev);
        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        if (nums.length == 1) return nums[0];

        // dp[i]表示nums[i]及前面的数组的最大自序和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
