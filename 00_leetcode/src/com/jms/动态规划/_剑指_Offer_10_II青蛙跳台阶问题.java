package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/5 10:39
 */
public class _剑指_Offer_10_II青蛙跳台阶问题 {

    public int numWays(int n) {
        final int MOD = 1000000007;

        if(n < 2) return 1;
        int n1 = 1, n2 = 2;

        for (int i = 2; i < n; i++) {
            n2 = n1 + n2;
            n1 = n2 - n1;
            n2 = n2 % MOD;
        }

        return n2;
    }
}
