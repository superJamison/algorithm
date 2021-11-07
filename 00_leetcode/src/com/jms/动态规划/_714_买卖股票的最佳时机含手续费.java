package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 20:42
 */
public class _714_买卖股票的最佳时机含手续费 {

    // 空间优化
    public int maxProfit(int[] prices, int fee) {
        if (prices.length < 2) return 0;

        // no不持有，have持有
        int no = 0, have = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            no = Math.max(no, have + prices[i] - fee);
            have = Math.max(have, no - prices[i]);
        }

        return no;
    }

    // 未做空间优化
    public int maxProfit1(int[] prices, int fee) {
        if (prices.length < 2) return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}
