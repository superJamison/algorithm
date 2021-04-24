package com.jms;

/**
 * n皇后问题    八皇后/四皇后
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/20 21:33
 */
public class Queens {

    public static void main(String[] args) {
        new Queens().placeQueens(4);
    }

    // 第index行的第value列上放置了皇后
    int[] cols;

    //一共有多少中解决方案
    int solutionCount;

    /**
     * @param n n皇后
     */
    private void placeQueens(int n) {
        if (n < 1) return;
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + solutionCount + "放置方案");
    }

    /**
     * 放置低row行皇后
     * @param row
     */
    private void place(int row) {
        //当row == n时，说明又有一种方案放置好了皇后
        if (row >= cols.length){
            solutionCount++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)){
                cols[row] = col;
                place(row + 1);
            }
        }
    }



    /**
     * 判断第row行第col列是否可以放置皇后
     * @return true：可以放置
     */
    private boolean isValid(int row, int col) {
        //遍历row行以前的行，这些行肯定已经放置好了皇后
        for (int i = 0; i < row; i++) {
            if (col == cols[i]) return false;
            if (row - i == Math.abs(col - cols[i])) return false;

        }
        return true;
    }

    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col){
                    System.out.print("1    ");
                }else {
                    System.out.print("0    ");
                }
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

}
