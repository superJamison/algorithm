package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/22 18:08
 */
public class 斜线遍历 {
    /**
     * 1    2   3   4
     * 5    6   7   8
     * 9    10  11  12
     * <p>
     * 0    1   2   3
     * 1    2   3   4
     * 2    3   4   5
     */
    public static void main(String[] args) {
        int[][] ints = {
                {1,  2,  3,  4,  55},
                {5,  6,  7,  8,  66},
                {9,  10, 11, 12, 77},
                {13, 14, 15, 16, 88},
                {17, 18, 19, 20, 99}
        };
        int[] reverse = reverse(ints);

        for (int i : reverse) {
            System.out.print(i + "  ");
        }
    }

    private static int[] reverse(int[][] grid) {
        if (grid == null || grid.length == 0) return null;

        int m = grid.length; // 3
        int n = grid[0].length; // 4
        int[] result = new int[n * m];

        int index = 0, x, y;
        for (int i = 0; i < m + n - 1; i++) {
            if (i >= n) {
                x = i - n + 1;
                y = n - 1;
            } else {
                x = 0;
                y = i;
            }

            while (x < m && y >= 0) {
                result[index++] = grid[x][y];
                x++;
                y--;
            }
        }
        return result;
    }


}
