package com.jms;

/**
 * n皇后问题优化进一步运用位运算优化  八皇后/四皇后
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/20 21:33
 */
public class Queens2 {

    public static void main(String[] args) {
        new Queens2().place8Queens();
    }

    int[] queens;
    // 第几列有皇后则为true
    byte cols;
    //从右上角到左下角的第几根斜线上有皇后则为true
    short rightTop;
    //从左上角到右下角的第几根斜线上有皇后则为true
    short leftTop;

    //一共有多少中解决方案
    int solutionCount;

    /**
     * 8皇后
     */
    private void place8Queens() {
        queens = new int[8];
        place(0);
        System.out.println("8皇后一共有" + solutionCount + "放置方案");
    }

    /**
     * 放置低row行皇后
     * @param row
     */
    private void place(int row) {
        //当row == n时，说明又有一种方案放置好了皇后
        if (row >= 8){
            solutionCount++;
            show();
            return;
        }

        for (int col = 0; col < 8; col++) {
            int cv = 1 << col;
            if ((cols & cv) != 0) continue;
            int rv = 1 << (row + col);
            if ((rightTop & rv) != 0) continue;
            int lv = 1 << (row - col + 7);
            if ((leftTop & lv) != 0) continue;

            cols |= cv;
            rightTop |= rv;
            leftTop |= lv;
            queens[row] = col;
            place(row + 1);
            //恢复现场
            cols  &= ~cv;
            rightTop &= ~rv;
            leftTop &= ~lv;
        }
    }

    private void show() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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
