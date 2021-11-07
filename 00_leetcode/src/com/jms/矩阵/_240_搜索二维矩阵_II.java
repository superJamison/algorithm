package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/25 15:11
 */
public class _240_搜索二维矩阵_II {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int row = 0, col = matrix[0].length - 1;

        while (true){
            if (row >= matrix.length || col < 0) break;
            if (matrix[row][col] > target){
                col--;
            }else if (matrix[row][col] < target){
                row++;
            }else {
                return true;
            }
        }

        return false;
    }
}
