package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 17:07
 */
public class _1014_最佳观光组合 {

    public int maxScoreSightseeingPair(int[] values) {
        int result = Integer.MIN_VALUE, left = values[0];

        for (int i = 1; i < values.length; i++) {
            result = Math.max(result, left + values[i] - i);
            left = Math.max(left, values[i] + i);
        }

        return result;
    }
}
