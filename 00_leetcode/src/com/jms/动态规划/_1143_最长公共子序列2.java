package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/31 10:55
 */
public class _1143_最长公共子序列2 {

    public static void main(String[] args) {
        _1143_最长公共子序列2 v = new _1143_最长公共子序列2();
        System.out.println(v.longestCommonSubsequence("abcde", "ace"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || "".equals(text1)) return 0;
        if (text2 == null || "".equals(text2)) return 0;

        int row = text1.length();
        int col = text2.length();
        // dp[i][j] 表示以text1[i]结尾并以text2[j]结尾的字符串的最长公共子序列
        int[][] dp = new int[row][col];
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        // 初始化第0行和第0列
        boolean flag = false;
        for (int j = 0; j < col; j++) {
            if (flag) {
                dp[0][j] = 1;
                continue;
            }
            if (chars1[0] == chars2[j]) {
                dp[0][j] = 1;
                flag = true;
            }
        }
        flag = false;
        for (int i = 0; i < row; i++) {
            if (flag) {
                dp[i][0] = 1;
                continue;
            }
            if (chars1[i] == chars2[0]) {
                dp[i][0] = 1;
                flag = true;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[row - 1][col - 1];
    }
}
