package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/26 22:35
 */
public class _509_斐波那契数 {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n <= 2) return n;

        int small = 1, big = 1;
        for (int i = 3; i <= n; i++) {
            big = big + small;
            small = big - small;
        }
        return big;
    }
}
