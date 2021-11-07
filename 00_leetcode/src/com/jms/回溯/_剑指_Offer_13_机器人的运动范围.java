package com.jms.回溯;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/11 11:52
 */
public class _剑指_Offer_13_机器人的运动范围 {

    public static void main(String[] args) {
        _剑指_Offer_13_机器人的运动范围 v = new _剑指_Offer_13_机器人的运动范围();
        System.out.println(v.movingCount(16, 8, 4));
    }

    int count = 0;

    public int movingCount(int m, int n, int k) {
        if (k == 0) return 1;

        boolean[][] grid = new boolean[m][n];

        dfs(grid, 0, 0, k);

        return count;
    }

    private void dfs(boolean[][] grid, int i, int j, int k) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j]) return;

        int temp = i, sum = 0;

        while (temp != 0) {
            sum += temp % 10;
            temp /= 10;
        }
        temp = j;
        while (temp != 0) {
            sum += temp % 10;
            temp /= 10;
        }

        if (sum <= k && !grid[i][j]) {
            count++;
            grid[i][j] = true;
            dfs(grid, i + 1, j, k);
            dfs(grid, i, j + 1, k);
        }

    }
}
