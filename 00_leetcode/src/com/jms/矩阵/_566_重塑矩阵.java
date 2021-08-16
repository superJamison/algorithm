package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 9:49
 */
public class _566_重塑矩阵 {

    public static void main(String[] args) {
        int[][] ints = new int[2][2];

        int num = 1;

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = num++;
            }
        }

        int[][] mat = matrixReshape(ints, 2, 3);

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public static int[][] matrixReshape(int[][] mat, int row, int col) {
        // 判断旧矩阵是？* ？的
        int oldCol = mat[0].length;
        if (row * col != mat.length * oldCol){
            return mat;
        }

        // 进行转换，构造一个row行col列的矩阵
        int[][] result = new int[row][col];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = mat[index / oldCol][index % oldCol];
                index++;
            }
        }

        return result;
    }

    public static int[][] matrixReshape1(int[][] mat, int row, int col) {
        if (mat.length == 0) return mat;
        if (mat[0].length == 0) return mat;

        // 判断旧矩阵是？* ？的
        int n = mat.length;
        int m = mat[0].length;
        if (row * col != n * m){
            return mat;
        }

        // 进行转换，构造一个row行col列的矩阵
        int[][] result = new int[row][col];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = mat[index / m][index % m];
                index++;
            }
        }

        return result;
    }
}
