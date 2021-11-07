package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/1 10:24
 */
public class _322_零钱兑换 {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // 凑成i块钱所需的最少硬币个数
        int[] dp = new int[amount + 1];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = (int) 1e9;
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == (int) 1e9 ? -1 : dp[amount];
    }
}
