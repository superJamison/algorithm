package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/7 11:41
 */
public class _598_范围求和_II {

    public int maxCount(int m, int n, int[][] ops) {
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;

        for (int[] op : ops) {
            xMin = Math.min(xMin, op[0]);
            yMin = Math.min(yMin, op[1]);
        }
        xMin = Math.min(xMin, m);
        yMin = Math.min(yMin, n);

        return xMin * yMin;
    }
}
