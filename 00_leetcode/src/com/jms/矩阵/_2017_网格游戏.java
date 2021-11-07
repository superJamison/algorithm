package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/5 0:00
 */
public class _2017_网格游戏 {

    public static void main(String[] args) {
        _2017_网格游戏 v = new _2017_网格游戏();
        int[][] ints = {
                {1, 3, 1, 15},
                {1, 3, 3, 1}};
        long l = v.gridGame(ints);
        System.out.println(l);
    }

    // 2ms
    public long gridGame(int[][] grid) {
        int c = grid[0].length;

        long row1 = 0;
        for (int j = 0; j < c; j++) {
            row1 += grid[0][j];
        }

        long row2 = 0;
        for (int i = 0; i < c; i++) {
            if (i >= 1 && row2 + grid[1][i - 1] >= row1 - grid[0][i]) {
               return Math.min(row1, row2 + grid[1][i - 1]);
            }
            row1 -= grid[0][i];
            row2 += i == 0 ? 0 : grid[1][i - 1];

        }

        return 0;
    }

    // 4ms
    public long gridGame1(int[][] grid) {
        int c = grid[0].length;

        long min = Long.MAX_VALUE;

        long row1 = 0;
        for (int j = 0; j < c; j++) {
            row1 += grid[0][j];
        }

        long row2 = 0;
        for (int i = 0; i < c; i++) {
            row1 -= grid[0][i];
            row2 += i == 0 ? 0 : grid[1][i - 1];
            min = Math.min(min, Math.max(row1, row2));
        }

        return min;
    }
}
