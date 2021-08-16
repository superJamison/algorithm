package com.jms.动态规划;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/27 20:28
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 *
 * 原题：给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 转化为：给定一个区间集合，找到互不重叠的区间的最大数量.
 * 设计状态转移方程：假设 j < i , 0~j之间的区间的最大数量是f(j)，
 *      所以只要比较第i个区间是否可以和第j个区间构成互不重叠，然后去较大值f[i] = Math.max(f[i], f[j] + 1);
 */
public class _435_无重叠区间 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{{2, 3}, {1, 2}, {4, 5}, {3, 4}};

        int count = eraseOverlapIntervals(ints);
        System.out.println(count);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0){
            return 0;
        }

        // 按每个区间的右端从小到大排序
        Arrays.sort(intervals, (int[] o1, int[] o2) -> o1[0] - o2[0]);

//        Arrays.stream(intervals).forEach(interval -> {
//            Arrays.stream(interval).forEach(num -> System.out.print(num + " "));
//            System.out.println();
//        });

        int n = intervals.length;
        // f(i)保存intervals的0到第i个区间的不重叠的最大数量
        int[] f = new int[n];
        Arrays.fill(f, 1);
        // 从第一个开始
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]){
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }

        return n - Arrays.stream(f).max().getAsInt();
    }
}
