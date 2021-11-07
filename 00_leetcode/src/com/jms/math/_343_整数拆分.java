package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/27 17:37
 */
public class _343_整数拆分 {

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }

        return dp[n];
    }

    public int integerBreak1(int n) {
        if (n <= 3) return n - 1;

        int resut = 1;
        int a = n / 3, b = n % 3;
        if (b == 1) return (int) (Math.pow(3, a - 1) * 4);
        if (b == 2) return (int) (Math.pow(3, a) * 2);
        return (int) Math.pow(3, a);
    }
}
