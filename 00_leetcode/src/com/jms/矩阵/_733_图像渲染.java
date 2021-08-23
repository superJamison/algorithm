package com.jms.矩阵;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/23 11:07
 */
public class _733_图像渲染 {

    public static void main(String[] args) {
//        int[][] ints = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
//        int[][] result = floodFill(ints, 1, 1, 2);

        int[][] ints = {{0, 0, 0}, {0, 1, 1}};
        int[][] result = floodFill(ints, 1, 1, 1);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "   ");
            }
            System.out.println();
        }
    }

    /**
     * 非递归（迭代）
     * 执行用时：2 ms, 在所有 Java 提交中击败了17.25%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了94.78%的用户
     *
     * @param image    图像
     * @param sr       行
     * @param sc       列
     * @param newColor 新的像素
     * @return 新的图像
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) return image;

        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int r = arr[0];
            int c = arr[1];

            image[r][c] = newColor;

            // 上
            if ((r - 1) >= 0 && image[r - 1][c] == oldColor) {
                queue.add(new int[]{r - 1, c});
            }
            // 下
            if ((r + 1) < image.length && image[r + 1][c] == oldColor) {
                queue.add(new int[]{r + 1, c});
            }
            // 左
            if ((c - 1) >= 0 && image[r][c - 1] == oldColor) {
                queue.add(new int[]{r, c - 1});
            }
            // 右
            if ((c + 1) < image[0].length && image[r][c + 1] == oldColor) {
                queue.add(new int[]{r, c + 1});
            }
        }

        return image;
    }

    /**
     * 递归
     * 执行用时：2 ms, 在所有 Java 提交中击败了100%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了17%的用户
     *
     * @param image    图像
     * @param sr       行
     * @param sc       列
     * @param newColor 新的像素
     * @return 新的图像
     */
    public static int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) return image;

        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        dyeing(image, sr, sc, newColor, oldColor);

        return image;
    }

    public static void dyeing(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (sr >= 0 && sc >= 0 && image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;

            // 上
            if ((sr - 1) >= 0) {
                dyeing(image, sr - 1, sc, newColor, oldColor);
            }
            // 下
            if ((sr + 1) < image.length) {
                dyeing(image, sr + 1, sc, newColor, oldColor);
            }
            // 左
            if ((sc - 1) >= 0) {
                dyeing(image, sr, sc - 1, newColor, oldColor);
            }
            // 右
            if ((sc + 1) < image[0].length) {
                dyeing(image, sr, sc + 1, newColor, oldColor);
            }
        }
    }

}
