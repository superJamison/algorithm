package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/24 12:07
 */
public class _1143_最长公共子序列 {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        char[] chars1 = text1.toCharArray();
        if (chars1.length == 0) return 0;
        char[] chars2 = text2.toCharArray();
        if (chars2.length == 0) return 0;

        char[] rowChars = chars1, colChars = chars2;
        if (chars1.length < chars2.length){
            rowChars = chars2;
            colChars = chars1;
        }
        int[] dp = new int[colChars.length + 1];
        for (int i = 1; i <= rowChars.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colChars.length; j++) {
                int leftTop = cur;
                //保存之前的dp[j]，留作下一轮循环作为leftTop
                cur = dp[j];
                if (rowChars[i - 1] == colChars[j - 1]){
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colChars.length];
    }
}
