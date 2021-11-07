package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/30 15:06
 */
public class _673_最长递增子序列的个数2 {

    public static void main(String[] args) {
        _673_最长递增子序列的个数2 v = new _673_最长递增子序列的个数2();
        System.out.println(v.findNumberOfLIS(new int[]{2,2,2,2,2}));
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]表示以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[n];
        // counts[i]表示以nums[i]结尾的最长递增子序列的个数
        int[] counts = new int[n];
        dp[0] = 1;
        counts[0] = 1;
        int maxLen = 1, result = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            counts[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        // 重置计数
                        counts[i] = counts[j];
                    }else if (dp[j] + 1 == dp[i]){
                        counts[i] += counts[j];
                    }
                }
            }
            if (dp[i] > maxLen){
                maxLen = dp[i];
                // 重置计数
                result = counts[i];
            }else if (dp[i] == maxLen){
                result += counts[i];
            }
        }
        return result;
    }
}
