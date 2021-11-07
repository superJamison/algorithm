package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/6 10:07
 */
public class _剑指_Offer_42_连续子数组的最大和 {

    public static void main(String[] args) {
        _剑指_Offer_42_连续子数组的最大和 v = new _剑指_Offer_42_连续子数组的最大和();

        v.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // prev 前一个最大和
        int max = nums[0], prev = 0;

        for (int i = 0; i < nums.length; i++) {
           prev = Math.max(nums[i] + prev, nums[i]);
           max = Math.max(max, prev);
        }

        return max;
    }

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
           dp[i] = Math.max(nums[i] + dp[i - 1], nums[i]);
           max = Math.max(max, dp[i]);
        }
        max = Math.max(max, dp[0]);

        return max;
    }
}
