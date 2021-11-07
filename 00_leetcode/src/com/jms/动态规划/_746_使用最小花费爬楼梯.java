package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/30 20:16
 */
public class _746_使用最小花费爬楼梯 {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length, a = cost[0], b = cost[1], old;

        for (int i = 2; i <= n; i++) {
            old = b;
            b = Math.min(a, b) + (i == n ? 0 : cost[i]);
            a = old;
        }
        return b;
    }
}
