package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/23 12:06
 * https://leetcode-cn.com/problems/max-area-of-island/
 */
public class _695_岛屿的最大面积 {

    public static void main(String[] args) {
//        int[][] ints =
//               {{0,0,1,0,0,0,0,1,0,0,0,0,0},
//                {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                {0,1,1,0,1,0,0,0,0,0,0,0,0},
//                {0,1,0,0,1,1,0,0,1,0,1,0,0},
//                {0,1,0,0,1,1,0,0,1,1,1,0,0},
//                {0,0,0,0,0,0,0,0,0,0,1,0,0},
//                {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        int[][] ints = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        System.out.println(maxAreaOfIsland(ints));
    }

    /**
     * 递归
     *
     * @param grid
     * @return
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result = Math.max(dfs(grid, i, j), result);
                }
            }
        }

        return result;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) return 0;

        grid[i][j] = 0;

        return dfs(grid, i, j - 1) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + 1;
    }


}
