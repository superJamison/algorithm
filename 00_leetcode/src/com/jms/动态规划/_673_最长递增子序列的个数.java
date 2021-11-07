package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/15 0:19
 */
public class _673_最长递增子序列的个数 {

    public static void main(String[] args) {
        _673_最长递增子序列的个数 v = new _673_最长递增子序列的个数();
        v.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2});
        // 1 2 3 3 4 4 5 2
        // 1 1 1 1 2 2 4 1
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // dp[i]表示以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int index = i;

            while (index > 0) {
                index--;
                if (nums[index] < nums[i]) {
                    dp[i] = dp[index];
                    break;
                }
            }
            dp[i] = dp[i] + 1;
        }

        int count1 = 0, count2 = 0, max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
            System.out.println(i);
        }
        for (int i : dp) {
            if (max == i) count1++;
            if (max - 1 == i) count2++;
        }

        return count2 == 0 ? count1 : count1 * count2;
    }
}
