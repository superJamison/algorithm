package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/5 10:09
 */
public class _剑指_Offer_10_I斐波那契数列 {

    public static void main(String[] args) {
        _剑指_Offer_10_I斐波那契数列 v = new _剑指_Offer_10_I斐波那契数列();
        System.out.println(v.fib(45));
        System.out.println(Integer.MAX_VALUE);
    }

    public int fib(int n) {
        final int MOD = 1000000007;
        if (n == 0) return 0;
        // 1 1 2 3 5 8 13 21 34
        long f1 = 1, f2 = 1;
        for (int i = 2; i < n; i++) {
            f2 = f1 + f2;
            f1 = f2 - f1;
            f2 = f2 % MOD;
        }
        return (int) f2;
    }
}
