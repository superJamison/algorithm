package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/26 23:48
 */
public class _1137_第_N个泰波那契数 {

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        int a = 0, b = 1, c = 1;

        for (int i = 3; i <= n; i++) {
            c = a + b + c;
            b = c - a - b;
            a = c - a - b;
        }

        return c;
    }
}
