package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/30 11:39
 */
public class _300_最长递增子序列 {

    public static void main(String[] args) {
        _300_最长递增子序列 v = new _300_最长递增子序列();
        System.out.println(v.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    // 动态规划+二分查找
    public int lengthOfLIS1(int[] nums) {
        int[] tails = new int[nums.length];
        int result = 0;
        for (int num : nums) {
            int i = 0, j = result;
            // 找到一个位置index，tails[index - 1] < num < tails[index]，就将num赋值给tials[index]
            while (i < j){
                int mid = (i + j) / 2;
                if (tails[mid] < num) i = mid + 1;
                else j = mid;
            }
            tails[i] = num;
            if (j == result) result++;
        }
        return result;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // dp[i]表示以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[n];
        dp[0] = 1;
        int result = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
