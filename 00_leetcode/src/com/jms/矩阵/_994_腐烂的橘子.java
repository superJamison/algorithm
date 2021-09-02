package com.jms.矩阵;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * @author Jamison
 * @version 1.0
 * @date 2021/8/25 22:28
 * https://leetcode-cn.com/problems/rotting-oranges/submissions/
 */
public class _994_腐烂的橘子 {
    public static void main(String[] args) {
        int[][] ints = {{2,1,1},{1,1,0},{0,1,1}};
//        int[][] ints = {{2,1,1},{0,1,1},{1,0,1}};
        int result = orangesRotting2(ints);
        System.out.println(result);
    }

    public static int orangesRotting2(int[][] grid){
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        int cnt1 = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(i * n + j); // 将腐烂的橘子入队
                } else if (grid[i][j] == 1) {
                    cnt1++; // 统计新鲜橘子的数量
                }
            }
        }

        int time = 0;
        while (!queue.isEmpty() && cnt1 > 0) { // 当队列空了 或者 没有新鲜橘子了，停止循环
            time++; // 一层一层的传染，每传染一层，时间+1
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                int x = p / n, y = p % n;
                if (x - 1 >= 0 && grid[x - 1][y] == 1) { // 上
                    cnt1--; // 每传染一个，更新新鲜橘子的数量
                    grid[x - 1][y] = 2;
                    queue.offer((x - 1) * n + y);
                }
                if (x + 1 < m && grid[x + 1][y] == 1) { // 下
                    cnt1--;
                    grid[x + 1][y] = 2;
                    queue.offer((x + 1) * n + y);
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 1) { // 左
                    cnt1--;
                    grid[x][y - 1] = 2;
                    queue.offer(x * n + y - 1);
                }
                if (y + 1 < n && grid[x][y + 1] == 1) { // 右
                    cnt1--;
                    grid[x][y + 1] = 2;
                    queue.offer(x * n + y + 1);
                }
            }
        }

        return cnt1 == 0? time: -1;
    }

    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

        int m = grid.length, n = grid[0].length;
        // 用来存储腐烂的橘子
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    // 将二维数组的索引转换为一维数组的索引
                    queue.add(new int[]{i, j});
                }
            }
        }

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, -1, 0, 1};

        int result = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int isHit = 0;

            while (size-- > 0){
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int ni = poll[0] + di[k];
                    int nj = poll[1] + dj[k];
                    if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 1){
                        grid[ni][nj] = 2;
                        queue.add(new int[]{ni, nj});
                        isHit = 1;
                    }
                }
            }
            result += isHit;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    return -1;
                }
            }
        }

        return result;
    }

    public static int orangesRotting1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

        int m = grid.length, n = grid[0].length;
        // 用来存储腐烂的橘子
        Queue<Integer> queue = new LinkedList<>();
        // 存储橘子腐烂的天数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    // 将二维数组的索引转换为一维数组的索引
                    int index = n * i + j;
                    queue.add(index);
                    map.put(index, 0);
                }
            }
        }

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, -1, 0, 1};

        int result = 0;
        while (!queue.isEmpty()){
            int index = queue.poll();
            int i = index / n;
            int j = index % n;
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 1){
                    grid[ni][nj] = 2;
                    int nIndex = ni * n + nj;
                    queue.add(nIndex);
                    map.put(nIndex, map.get(index) + 1);
                    result = map.get(nIndex);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    return -1;
                }
            }
        }

        return result;
    }
}
