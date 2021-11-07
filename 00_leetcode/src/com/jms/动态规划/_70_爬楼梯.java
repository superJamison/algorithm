package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/28 21:21
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class _70_爬楼梯 {
    public static void main(String[] args) {
        int result = climbStairs(10);
        System.out.println(result);
    }

    public static int climbStairs(int n) {
        if (n <= 2) return n;

        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            b = a + b;
            a = b - a;
        }

        return b;
    }

    public static int climbStairs3(int n) {
        if (n <= 2) return n;

        int small = 1, big = 1;

        for (int i = 2; i <= n; i++) {
            big = big + small;
            small = big - small;
        }

        return big;
    }

    public static int climbStairs2(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int climbStairs1(int n) {
        if (n <= 1) return n;

        int result = dp(n);

        return result;
    }

    private static int dp(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        return dp(n - 1) + dp(n - 2);
    }
}
