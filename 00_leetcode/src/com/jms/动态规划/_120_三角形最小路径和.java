package com.jms.动态规划;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/29 20:43
 * https://leetcode-cn.com/problems/triangle/
 */
public class _120_三角形最小路径和 {
    public static void main(String[] args) {
        int[][] ints = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        List<List<Integer>> list = new ArrayList();
        for (int[] anInt : ints) {
            List<Integer> collect = Arrays.stream(anInt).boxed().collect(Collectors.toList());
            list.add(collect);
        }
        int result = minimumTotal(list);
        System.out.println(result);
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // 总共有多少行
        int size = triangle.size();
        if (triangle == null || size == 0) return 0;

        // <第row层，<第col列，最小路径和>>
        int[] dp = new int[size];

        for (int i = 0; i < size; i++) {
            dp[i] = triangle.get(size - 1).get(i);
        }

        // 从倒数第二行开始
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        // <第row层，<第col列，最小路径和>>
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();


        Map<Integer, Integer> triangleMap = new HashMap<>();
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            triangleMap.put(i, triangle.get(triangle.size() - 1).get(i));
        }
        map.put(triangle.size() - 1, triangleMap);

        // 从倒数第二行开始
        for (int i = triangle.size() - 2; i >= 0; i--) {
            Map<Integer, Integer> triangleMap2 = new HashMap<>();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int min = Math.min(map.get(i + 1).get(j), map.get(i + 1).get(j + 1)) + triangle.get(i).get(j);
                triangleMap2.put(j, min);
            }
            map.put(i, triangleMap2);
        }

        return map.get(0).get(0);
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        int result = mininum(triangle, 0, 0);

        return result;
    }

    private static int mininum(List<List<Integer>> triangle, int row, int col) {
        if (row >= triangle.size() || col >= triangle.get(triangle.size() - 1).size()) return 0;

        return Math.min(mininum(triangle, row + 1, col), mininum(triangle, row + 1, col + 1)) + triangle.get(row).get(col);
    }
}
