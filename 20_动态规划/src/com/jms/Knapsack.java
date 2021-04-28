package com.jms;

/**
 * 0-1背包问题
 * 思路：
 * 1.假设dp(i, j) 代表最大承重为j，有前i件物品可选时的最大总价值
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/24 19:13
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 20;
        System.out.println(maxValueExactly(weights, values, capacity));
    }

    /**
     * 求0-1背包问题的最大价值
     */
    private static int maxValueExactly(int[] weights, int[] values, int capacity) {
        if (weights == null || weights.length == 0) return 0;
        if (values == null || values.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= capacity; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= values.length; i++) {
            //从右往左开始求容量为j的背包里装前i个可选物品的最大价值
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

    /**
     * 求0-1背包问题的最大价值
     */
    private static int maxValue(int[] weights, int[] values, int capacity) {
        if (weights == null || weights.length == 0) return 0;
        if (values == null || values.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            //从右往左开始求容量为j的背包里装前i个可选物品的最大价值
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }

    /**
     * 求0-1背包问题的最大价值
     */
    private static int maxValue2(int[] weights, int[] values, int capacity) {
        if (weights == null || weights.length == 0) return 0;
        if (values == null || values.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            //从右往左开始求容量为j的背包里装前i个可选物品的最大价值
            for (int j = capacity; j >= 1; j--) {
                if (j < weights[i - 1]) continue;
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }

    private static int maxValue1(int[] weights, int[] values, int capacity) {
        if (weights == null || weights.length == 0) return 0;
        if (values == null || values.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;

        int[][] dp = new int[values.length + 1][capacity + 1];
        int max = 0;
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    //当第i件物品的重量大于容量j时，表明背包装不下这个物品，就将这个物品直接舍去
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //比较舍去第i个物品的价值和装上第i个物品的价值大小，选择较大的那个
                    dp[i][j] = Math.max(dp[i - 1][j],
                            values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
}
