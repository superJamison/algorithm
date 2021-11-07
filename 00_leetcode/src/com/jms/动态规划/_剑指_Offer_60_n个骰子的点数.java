package com.jms.动态规划;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/26 15:12
 */
public class _剑指_Offer_60_n个骰子的点数 {

    public static void main(String[] args) {
        _剑指_Offer_60_n个骰子的点数 v = new _剑指_Offer_60_n个骰子的点数();
        double[] doubles = v.dicesProbability(2);

        for (double aDouble : doubles) {
            System.out.print(aDouble + "  ");
        }
    }

    public double[] dicesProbability1(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6);

        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                double v = dp[j] / 6.0;
                for (int x = 0; x < 6; x++) {
                    temp[x + j] += v;
                }
            }
            dp = temp;
        }

        return dp;
    }

    public double[] dicesProbability(int n) {
        int[] dp = new int[n * 6 + 1];

        for (int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            int[] temp = new int[dp.length];
            for (int j = 1; j <= (i - 1) * 6; j++) {
                for (int x = 1; x <= 6; x++) {
                    temp[x + j] += dp[j];
                }
            }
            dp = temp;
        }

        double total = 0;
        for (int i : dp) {
            total += i;
        }
        double[] result = new double[dp.length - n];
        for (int i = 0; i < result.length; i++) {
            result[i] = dp[i + n] / total;
        }

        return result;
    }
}
