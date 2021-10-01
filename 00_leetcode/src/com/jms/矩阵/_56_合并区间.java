package com.jms.矩阵;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/1 22:02
 */
public class _56_合并区间 {

    public static void main(String[] args) {
        int[][] ints = {{1,2}};
        int[][] merge = merge(ints);

        for (int[] ints1 : merge) {
            for (int i : ints1) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;

        List<int[]> list = new ArrayList<>();

        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        for (int i = 0; i < intervals.length; i++) {
            if (i + 1 < intervals.length && intervals[i][1] >= intervals[i + 1][0]){
                intervals[i + 1][0] = Math.min(intervals[i][0], intervals[i + 1][0]);
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i +1][1]);
            }else {
                list.add(new int[]{intervals[i][0], intervals[i][1]});
            }
        }

        return list.toArray(new int[list.size()][2]);
    }
}
