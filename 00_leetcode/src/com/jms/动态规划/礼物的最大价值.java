package com.jms.动态规划;

/**
 * 在一个m*n的棋盘的每一格都放有一个礼物，每一个礼物都有一定的价值（>0）。你可以从棋盘的走下角开始拿礼物，
 * 并且每次向下或者向右移动一格，直到到达左下角。给定一个棋盘及其格子上的礼物价值，请计算你最多能拿到多少价值的礼物？
 * [
 *      [1, 3, 1],
 *      [1, 5, 1],
 *      [4, 2, 1]
 * ]
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/5/1 11:19
 */
public class 礼物的最大价值 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(maxValue(grid));
    }

    public static int maxValue(int[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows == 0 || cols == 0) return 0;
        // dp[i][j]代表走到第i行第j列的最大价值
        int[][] dp = new int[2][cols];
        dp[0][0] = grid[0][0];
        // 第0行
        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col - 1] + grid[0][col];
        }
        // 第0列
        for (int row = 1; row < rows; row++) {
            dp[row & 1][0] = dp[(row - 1) & 1][0] + grid[row][0];
        }
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dp[row & 1][col] = Math.max(dp[row & 1][col - 1], dp[(row - 1) & 1][col]) + grid[row][col];
            }
        }
        return dp[(rows - 1) & 1][cols - 1];
    }

    public static int maxValue2(int[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows == 0 || cols == 0) return 0;
        // dp[i][j]代表走到第i行第j列的最大价值
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        // 第0行
        for (int i = 1; i < cols; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 第0列
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dp[row][col] = Math.max(dp[row][col - 1], dp[row - 1][col]) + grid[row][col];
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
