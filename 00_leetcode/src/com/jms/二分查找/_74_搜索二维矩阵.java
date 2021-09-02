package com.jms.二分查找;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/31 22:33
 */
public class _74_搜索二维矩阵 {
    public static void main(String[] args) {
        int[][] ints = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(ints, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int colSize = matrix[0].length;
        if (matrix[0] == null || colSize == 0) return false;

        int left = 0, right = colSize * matrix.length - 1;

        int row, col;
        while (left <= right){
            int mid = (left + right) >> 1;
            row = mid / colSize;
            col = mid % colSize;
            if (target == matrix[row][col] ) {
                return true;
            }else if (target < matrix[row][col]) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return false;
    }
}
