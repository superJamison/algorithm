package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 20:25
 */
public class _309_最佳买卖股票时机含冷冻期 {

    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        // dp[i][0]表示第i天不持有股票的利润，dp[i][1]表示第i天持有股票的利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = prices[1] - prices[0] > 0 ? prices[1] - prices[0] : 0;
        dp[1][1] = -Math.min(prices[0], prices[1]);

        for (int i = 2; i < prices.length; i++) {
            // 第i天不持有股票，有可能是前一天不持有股票dp[i - 1][0]或者是前一天持有股票今天卖出了股票dp[i - 1][1] + prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天持有股票，有可能是前一天就是持有股票的dp[i - 1][1]或者是可能经过了以两个冷冻期今天再次购买了股票dp[i - 2][0] - prices[i]
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}
