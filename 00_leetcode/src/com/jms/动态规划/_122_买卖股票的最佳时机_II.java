package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 17:38
 */
public class _122_买卖股票的最佳时机_II {

    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        // no不持有，have持有
        int no = 0, have = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            no = Math.max(no, have + prices[i]);
            have = Math.max(have, no - prices[i]);
        }

        return no;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length < 2) return 0;

        // dp[i][0]表示第i天不持有股票的利润，dp[i][1]表示第i天持有股票的利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 贪心算法，一次遍历，只要今天价格小于明天价格就在今天买入然后明天卖出，时间复杂度O(n)
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int result = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]){
                result += prices[i] - prices[i - 1];
            }
        }

        return result;
    }
}
