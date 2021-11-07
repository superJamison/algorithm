package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/5 10:49
 */
public class _剑指_Offer_63_股票的最大利润 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int minPrices = prices[0];
        int maxProfie = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrices){
                minPrices = prices[i];
            }else {
                maxProfie = Math.max(maxProfie, prices[i] - minPrices);
            }
        }

        return maxProfie;
    }
}
