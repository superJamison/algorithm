package com.jms.模拟;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/22 10:21
 */
public class _剑指_Offer_29_顺时针打印矩阵 {

    public static void main(String[] args) {
        _剑指_Offer_29_顺时针打印矩阵 v = new _剑指_Offer_29_顺时针打印矩阵();
        int[][] ints = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        int[] result = v.spiralOrder(ints);

        for (int i : result) {
            System.out.print(i + "  ");
        }

    }

    public int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) return new int[0];
        int col = matrix[0].length;
        int[] result = new int[row * col];

        int i = 0, j = 0;
        int l = 0, r = col - 1, up = 0, down = row - 1;
        int index = 0;
        // 1 向右  -1 向左   2 向下 -2 向上
        int direct = 1;

        while (true) {
            if (index >= result.length) break;
            result[index++] = matrix[i][j];
            if (l > r || up > down) break;
            if (direct == 1) {
                j++;
                if (j == r) {
                    direct = 2;
                    up++;
                }
                if (j > r) {
                    j = r;
                    i++;
                }
            } else if (direct == -1) {
                j--;
                if (j == l) {
                    direct = -2;
                    down--;
                }
                if (j < l) {
                    j = l;
                    i--;
                }
            } else if (direct == 2) {
                i++;
                if (i == down) {
                    direct = -1;
                    r--;
                }
                if (i > down) {
                    i = down;
                    j++;
                }
            } else if (direct == -2) {
                i--;
                if (i == up) {
                    direct = 1;
                    l++;
                }
                if (i < up) {
                    i = up;
                    j--;
                }
            }
        }

        return result;
    }
}
