package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 11:13
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class _240_搜索二维矩阵II {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        boolean b = searchMatrix(matrix, 20);
        System.out.println(b);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;

        while (true) {
            // 从左下角开始查找
            if (row < 0 || col >= matrix[0].length) break;
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }

        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        // 暴力遍历法
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }
}
