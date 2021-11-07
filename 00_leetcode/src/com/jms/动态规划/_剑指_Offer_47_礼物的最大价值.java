package com.jms.动态规划;

import sun.misc.FpUtils;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/6 10:50
 */
public class _剑指_Offer_47_礼物的最大价值 {

    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        // r行，c列
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[2][c];
        dp[0][0] = grid[0][0];

        // 初始化第0行的dp
        for (int j = 1; j < c; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 求出第1行后面行的dp
        for (int i = 1; i < r; i++) {
            dp[i % 2][0] = grid[i][0] + dp[(i - 1) % 2][0];
            for (int j = 1; j < c; j++) {
                dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]) + grid[i][j];
            }
        }

        return dp[(r - 1) % 2][c - 1];
    }
}
