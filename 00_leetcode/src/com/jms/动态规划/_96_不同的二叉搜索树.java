package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/6 16:40
 */
public class _96_不同的二叉搜索树 {

    // 数学C[0]=1；C[n+1]=2(2n+1)C[n]/(n+2)
    public int numTrees(int n) {
        long C = 1;
        for (int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    // 动态规划
    public int numTrees1(int n) {
        int result = 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        // G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
