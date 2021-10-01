package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/30 20:22
 */
public class _223_矩形面积 {

    public static void main(String[] args) {
        int i = computeArea(-3, 0, 3, 4, 0, -1, 9, 4);
        System.out.println(i);
    }

    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int s1 = (ax2 - ax1) * (ay2 - ay1);

        int s2 = (bx2 - bx1) * (by2 - by1);

        // 无重叠的情况
        if (bx2 <= ax1 || bx1 >= ax2 || by2 <= ay1 || by1 >= ay2) return s1 + s2;

        int s3 = 0;

        int cx1 = Math.max(ax1, bx1);
        int cx2 = Math.min(ax2, bx2);
        int cy1 = Math.max(ay1, by1);
        int cy2 = Math.min(ay2, by2);

        s3 = (cx2 - cx1) * (cy2 - cy1);

        return s1 + s2 - s3;
    }
}
