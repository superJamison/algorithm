package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 11:37
 */
public class _1218_最长定差子序列 {

    public static void main(String[] args) {
        _1218_最长定差子序列 v = new _1218_最长定差子序列();
        System.out.println(v.longestSubsequence(new int[]{4, 12, 10, 0, -2, 7, -8, 9, -9, -12, -12, 8, 8}
                , 0));
    }

    public int longestSubsequence(int[] arr, int difference) {
        if (arr.length == 1) return 1;

        // dp[i]表示arr[i]及以前最长等差子序列的长度
        int result = 1;
        int n = 40001, m = n >> 1;
        int[] dp = new int[n];

        for (int i : arr) {
            dp[i + m] = dp[i - difference + m] + 1;
            result = Math.max(result, dp[i + m]);
        }

        return result;
    }
}
