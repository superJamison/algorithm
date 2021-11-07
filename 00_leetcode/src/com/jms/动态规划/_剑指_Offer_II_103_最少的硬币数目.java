package com.jms.动态规划;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/8 21:32
 */
public class _剑指_Offer_II_103_最少的硬币数目{

    public static void main(String[] args) {
        _剑指_Offer_II_103_最少的硬币数目 v = new _剑指_Offer_II_103_最少的硬币数目();
        int i = v.coinChange(new int[]{2}, 3);
        System.out.println(i);

        System.out.println(Integer.MAX_VALUE);
        System.out.println((int) 1e9);
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // dp[i] 代表凑够i元钱需要的最少硬币个数。
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            // 刚好这个硬币就是你要凑的面额
            if (coin < dp.length) {
                dp[coin] = 1;
            }
        }

        for (int i = 1; i <= amount; i++) {
            // 要凑够i元钱需要的最少硬币个数
            if (dp[i] == 0) dp[i] = (int) 1e9;

            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == (int) 1e9 ? -1 : dp[amount];
    }
}
