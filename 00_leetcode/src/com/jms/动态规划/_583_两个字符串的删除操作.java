package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/31 11:32
 */
public class _583_两个字符串的删除操作 {

    public static void main(String[] args) {
        _583_两个字符串的删除操作 v = new _583_两个字符串的删除操作();
        System.out.println(v.minDistance("sea", "eat"));
    }

    public int minDistance(String word1, String word2) {
        if (word1 == null || "".equals(word1)) return word2.length();
        if (word2 == null || "".equals(word2)) return word1.length();

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int row = word1.length();
        int col = word2.length();
        // dp[i][j] 表示使得word1前i个字符串和word2前j个字符串相同所需要的最少操作数
        int[][] dp = new int[row + 1][col + 1];
        for (int j = 0; j <= col; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= row; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (chars1[i - 1] == chars2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[row][col];
    }
}
