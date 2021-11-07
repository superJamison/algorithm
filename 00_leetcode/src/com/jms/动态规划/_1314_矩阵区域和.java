package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/7 15:33
 */
public class _1314_矩阵区域和 {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int r = mat.length, c = mat[0].length;
        int[][] dp = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int[][] reslut = new int[r][c];
        for (int i = 0; i < reslut.length; i++) {
            for (int j = 0; j < reslut[0].length; j++) {
                int iAddK = i + k + 1;
                int jAddK = j + k + 1;
                int iSubK = i - k;
                int jSubK = j - k;
                reslut[i][j] = dp[iAddK <= r ? iAddK : r][jAddK <= c ? jAddK : c] -
                        dp[iAddK <= r ? iAddK : r][jSubK >= 0 ? jSubK : 0] -
                        dp[iSubK > 0 ? iSubK : 0][jAddK <= c ? jAddK : c] +
                        dp[iSubK > 0 ? iSubK : 0][jSubK >= 0 ? jSubK : 0];
            }
        }

        return reslut;
    }
}
