package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/6 17:38
 */
public class _931_下降路径最小和 {

    public static void main(String[] args) {
        _931_下降路径最小和 v = new _931_下降路径最小和();
        int[][] ints = {{100,-42,-46,-41},{31,97,10,-10},{-58,-51,82,89},{51,81,69,-51}};
        System.out.println(v.minFallingPathSum(ints));
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(j == 0 ? Integer.MAX_VALUE : dp[i - 1][j - 1], Math.min(dp[i - 1][j], j == m - 1 ? Integer.MAX_VALUE : dp[i - 1][j + 1])) + matrix[i][j];
            }
        }

        int result = dp[n - 1][0];
        for (int j = 1; j < m; j++) {
            result = Math.min(result, dp[n - 1][j]);
        }

        return result;
    }
}
