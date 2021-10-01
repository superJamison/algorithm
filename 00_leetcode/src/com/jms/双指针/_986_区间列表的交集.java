package com.jms.双指针;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/3 21:50
 */
public class _986_区间列表的交集 {
    public static void main(String[] args) {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secodeList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] result = intervalIntersection(firstList, secodeList);

        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }

    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> list = new ArrayList<>();
        if (firstList == null || firstList.length == 0) return list.toArray(new int[list.size()][]);
        if (secondList == null || secondList.length == 0) return list.toArray(new int[list.size()][]);

        int firstIndex = 0, secodeIndex = 0;
        while (firstIndex < firstList.length && secodeIndex < secondList.length) {
            int[] first = firstList[firstIndex];
            int[] secode = secondList[secodeIndex];
            int[] ints = new int[2];

            if (secode[0] < first[0]) ints[0] = first[0];
            else ints[0] = secode[0];

            if (first[1] < secode[1]) {
                ints[1] = first[1];
                firstIndex++;
            } else {
                ints[1] = secode[1];
                secodeIndex++;
            }

            if (ints[0] <= ints[1]) {
                list.add(ints);
            }

        }

        return list.toArray(new int[list.size()][]);
    }
}
