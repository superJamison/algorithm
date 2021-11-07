package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/2 11:11
 */
public class _剑指_Offer_04_二维数组中的查找 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int r = matrix.length - 1, l = 0;

        while (true){
            if (r < 0 || l == matrix[0].length) break;

            if (matrix[r][l] == target) return true;
            else if (matrix[r][l] > target) r--;
            else l++;
        }

        return false;
    }


    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        return findNumber(matrix, target, 0, 0);
    }

    private boolean findNumber(int[][] matrix, int target, int r, int l) {
        if (r == matrix.length || l == matrix[0].length) return false;
        if (matrix[r][l] == target) return true;
        if (matrix[r][l] > target) return false;

        return findNumber(matrix, target, r + 1, l) || findNumber(matrix, target, r, l + 1);
    }
}
