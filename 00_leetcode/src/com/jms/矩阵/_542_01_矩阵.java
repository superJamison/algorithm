package com.jms.矩阵;

import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/25 20:33
 * https://leetcode-cn.com/problems/01-matrix/
 */
public class _542_01_矩阵 {
    public static void main(String[] args) {
//        int[][] ints = {{0,0,0},{0,1,0},{0,0,0}};
//        int[][] ints = {{0,0,0},{0,1,0},{1,1,1}};
//        int[][] ints = {
//                {0,1,0,1,1},
//                {1,1,0,0,1},
//                {0,0,0,1,0},
//                {1,0,1,1,1},
//                {1,0,0,0,1}};
        int[][] ints = {
                {0,0,1,0,1,1,1,0,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,1,0,0,0,1,1},
                {1,0,1,0,1,1,1,0,1,1},
                {0,0,1,1,1,0,1,1,1,1},
                {1,0,1,1,1,1,1,1,1,1},
                {1,1,1,1,0,1,0,1,0,1},
                {0,1,0,0,0,1,0,0,1,1},
                {1,1,1,0,1,1,0,1,0,1},
                {1,0,1,1,1,0,1,1,1,0}};

        int[][] result = updateMatrix(ints);

        if (result == null) return;

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return mat;

        int m = mat.length, n = mat[0].length;
        int[][] result = new int[m][n];
        int maxValue = Integer.MAX_VALUE >> 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0){
                    result[i][j] = 0;
                }else {
                    result[i][j] = maxValue;
                }
            }
        }

        // 右上
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if ((i - 1) >= 0){
                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                }
                if ((j + 1) < n){
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }

        // 左下
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if ((i + 1) < m){
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if ((j - 1) >= 0){
                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                }
            }
        }

        // 左上
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if ((i - 1) >= 0){
//                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
//                }
//                if ((j - 1) >= 0){
//                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
//                }
//            }
//        }
//
//        // 右下
//        for (int i = m - 1; i >= 0; i--) {
//            for (int j = n - 1; j >= 0; j--) {
//                if ((i + 1) < m){
//                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
//                }
//                if ((j + 1) < n){
//                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
//                }
//            }
//        }

        return result;
    }

    // 这个方法会导致StackOverflowError
    public static int[][] updateMatrix1(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return mat;

        int[][] result = new int[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0){
                    result[i][j] = 0;
                    continue;
                }

                int min = matrix(i, j, mat, 0);
                result[i][j] = min;
            }
        }


        return result;
    }

    // type:   1 up   2 down   3 left   4 right
    private static int matrix(int i, int j, int[][] mat, int type) {
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length) return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;

        // up
        if (type != 2 &&  (i - 1) >= 0){
            if (mat[i - 1][j] == 0){
                return 1;
            }else {
                min = Math.min(matrix(i - 1, j, mat, 1) + 1, min);
            }
        }

        if (min == 1) return 1;

        // left
        if (type != 4 && (j - 1) >= 0){
            if (mat[i][j - 1] == 0){
                return 1;
            }else {
                min = Math.min(matrix(i, j - 1, mat, 3) + 1, min);
            }
        }

        if (min == 1) return 1;

        // down
        if (type != 1 && (i + 1) < mat.length){
            if (mat[i + 1][j] == 0){
                return 1;
            }else {
                min = Math.min(matrix(i + 1, j, mat, 2) + 1, min);
            }
        }

        if (min == 1) return 1;

        // right
        if (type != 3 && (j + 1) < mat[0].length){
            if (mat[i][j + 1] == 0){
                return 1;
            }else {
                min = Math.min(matrix(i, j + 1, mat, 4) + 1, min);
            }
        }

        return min;
    }
}
