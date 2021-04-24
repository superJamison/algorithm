package com.jms;

/**
 * n皇后问题优化   八皇后/四皇后
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/20 21:33
 */
public class Queens1 {

    public static void main(String[] args) {
        new Queens1().placeQueens(4);
    }

    int[] queens;
    // 第几列有皇后则为true
    boolean[] cols;
    //从右上角到左下角的第几根斜线上有皇后则为true
    boolean[] rightTop;
    //从左上角到右下角的第几根斜线上有皇后则为true
    boolean[] leftTop;

    //一共有多少中解决方案
    int solutionCount;

    /**
     * @param n n皇后
     */
    private void placeQueens(int n) {
        if (n < 1) return;
        cols = new boolean[n];
        queens = new int[n];
        rightTop = new boolean[(n << 1) - 1];
        leftTop = new boolean[rightTop.length];
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
            if (cols[col]) continue;
            int rtIndex = row + col;
            if (rightTop[rtIndex]) continue;
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) continue;

            cols[col] = true;
            rightTop[rtIndex] = true;
            leftTop[ltIndex] = true;
            queens[row] = col;
            place(row + 1);

            cols[col] = false;
            rightTop[rtIndex] = false;
            leftTop[ltIndex] = false;
        }
    }

    private void show() {
        for (int row = 0; row < queens.length; row++) {
            for (int col = 0; col < queens.length; col++) {
                if (queens[row] == col){
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
