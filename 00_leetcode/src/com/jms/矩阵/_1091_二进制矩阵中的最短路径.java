package com.jms.矩阵;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/9 20:03
 */
public class _1091_二进制矩阵中的最短路径 {
    public static void main(String[] args) {
        int[][] ints =
                {{0, 1},
                 {1, 0}};

        int[][] ints1 = {
                {1,0,0},
                {1,1,0},
                {1,1,0}
        };
        int result = shortestPathBinaryMatrix(ints);
        System.out.println(result);
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int r = grid.length;
        int c = grid[0].length;
        if (grid[0][0] == 1 || grid[r - 1][c - 1] == 1) return -1;

        // 八个方向
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        // d[i][j]表示从（0,0）到（i,j）的最短距离
        int[][] d = new int[r][c];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = -1;
            }
        }
        d[0][0] = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            // 到达终点
            if (poll[0] == r && poll[1] == c) break;

            for (int i = 0; i < 8; i++) {
                int x = poll[0] + dr[i], y = poll[1] + dc[i];

                if (x >= 0 && y >= 0 && x < r && y < c && grid[x][y] == 0 && d[x][y] == -1) {
                    // 没有越界且之前没有访问过
                    queue.add(new int[]{x, y});
                    // 记录最短路径
                    d[x][y] = d[poll[0]][poll[1]] + 1;
                }
            }
        }

        return d[r - 1][c - 1];
    }

}
