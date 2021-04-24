package com.jms;

/**
 * 最大连续子序列和
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/23 22:45
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }



    /**
     * 空间复杂度O(1) 时间复杂度O(n)
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            }else {
                dp = dp + nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }

    /**
     * 空间复杂度O(n) 时间复杂度O(n)
     * @param nums
     * @return
     */
    private static int maxSubArray1(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            }else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
