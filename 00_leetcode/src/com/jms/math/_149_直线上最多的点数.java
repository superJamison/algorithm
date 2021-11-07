package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/3 16:46
 */
public class _149_直线上最多的点数 {

    public static void main(String[] args) {
        _149_直线上最多的点数 v = new _149_直线上最多的点数();
        int[][] ints = {{9, -25}, {-4, 1}, {-1, 5}, {-7, 7}};
        System.out.println(v.maxPoints(ints));
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int max = 2, cur;

        for (int i = 0; i < n; i++) {
            int[] x = points[i];
            if (max >= n - i) break;
            for (int j = i + 1; j < n; j++) {
                int[] y = points[j];
                cur = 2;
                int i2 = y[0] - x[0];
                int i4 = y[1] - x[1];
                for (int h = j + 1; h < n; h++) {
                    int[] z = points[h];
                    int i1 = z[0] - y[0];
                    int i3 = z[1] - y[1];
                    if (i1 * i4 == i3 * i2) {
                        cur++;
                    }
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }
}
