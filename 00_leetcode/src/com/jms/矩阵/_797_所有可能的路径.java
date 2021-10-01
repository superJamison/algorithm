package com.jms.矩阵;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/10 22:40
 */
public class _797_所有可能的路径 {
    public static void main(String[] args) {
        int[][] ints = {{1,2}, {3}, {3}, {}};
//        int[][] ints = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
//        int[][] ints = {{3,1},{4,6,7,2,5},{4,6,3},{6,4},{7,6,5},{6},{7},{}};
        List<List<Integer>> lists = allPathsSourceTarget(ints);

        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int n = graph.length;

        stack.push(0);
        dfs(graph, result, stack, 0, graph.length - 1);

        return result;
    }

    private static void dfs(int[][] graph, List<List<Integer>> result, Stack<Integer> stack, int i, int n) {
        if (i == n) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int value: graph[i]) {
            stack.push(value);
            dfs(graph, result, stack, value, n);
            stack.pop();
        }
    }

}
