package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/27 20:29
 */
public class _62_不同路径 {

    public static void main(String[] args) {
        _62_不同路径 v = new _62_不同路径();
        System.out.println(v.uniquePaths(3, 7));
    }

    public int uniquePaths(int m, int n) {
        long result = 1;

        for (int i = n, y = 1; y < m; i++, y++) {
            result = result * i / y;
        }
        return (int) result;
    }

    public int uniquePaths1(int m, int n) {
        if (m == 1 || n == 1) return 1;

        int[][] dp = new int[2][n];
        // 初始化第0行和第0列
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < 2; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i % 2][j] = dp[i % 2][j - 1] + dp[(i - 1) % 2][j];
            }
        }

        return dp[(m - 1) % 2][n - 1];
    }
}
