package com.jms.动态规划;

/**
 * https://leetcode-cn.com/problems/edit-distance/submissions/
 * @author Jamison
 * @version 1.0
 * @date 2021/5/2 22:34
 */
public class _72_编辑距离 {

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null){
            return 0;
        }
        if (word2.length() == 0){
            return word1.length();
        }
        if (word1.length() == 0){
            return word2.length();
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[] dp = new int[chars2.length + 1];
        for (int i = 1; i <= chars2.length; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= chars1.length; i++) {
            int cur = i - 1;
            for (int j = 1; j <= chars2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (chars1[i - 1] != chars2[j - 1]){
                    leftTop++;
                }
                dp[j] = Math.min(leftTop,
                            Math.min(dp[j] + 1, j == 1 ? i + 1 : dp[j - 1] + 1));
            }
        }
        return dp[chars2.length];
    }

    public int minDistance2(String word1, String word2) {
        if (word1 == null || word2 == null){
            return 0;
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        // 第0行第0列，空串转换为空串
        dp[0][0] = 0;
        // 第0列，char1[i]转换为空串，即将所有的字符删除
        for (int row = 1; row <= chars1.length; row++) {
            dp[row][0] = row;
        }
        // 第0行，空串转换为char2[i]，即一个一个添加char2的字符
        for (int col = 1; col <= chars2.length; col++) {
            dp[0][col] = col;
        }
        // 其他行其他列
        for (int row = 1; row <= chars1.length; row++) {
            for (int col = 1; col <= chars2.length; col++) {
                // 先删除char1[row - 1]位置的字符，再由char1[0, row - 1)转化为char2[0, col)所需要的操作数
                int top = dp[row - 1][col] + 1;
                // 先由char1[0, row)转化为char2[0, col - 1)，再添加char2[col]位置的字符所需要的操作数
                int left = dp[row][col - 1] + 1;
                // 由char1[0, row - 1)转化为char2[0, col - 1)所需要的操作数
                int leftTop = dp[row - 1][col - 1];
                // 若char1[row] == char2[col]则无需做任何操作，dp[row][col] = dp[row - 1][col - 1]
                // 若char1[row] != char2[col]则无需做任何操作，dp[row][col] = dp[row - 1][col - 1] + 1
                if (chars1[row - 1] != chars2[col - 1]){
                    leftTop++;
                }
                // 取前面三种情况的最小值即为dp[row][col]
                dp[row][col] = Math.min(leftTop, Math.min(top, left));
            }
        }
        return dp[chars1.length][chars2.length];
    }
}
