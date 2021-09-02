package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/26 21:58
 */
public class _77_组合 {
    public static void main(String[] args) {
        List<List<Integer>> combine = combine(4, 2);

        System.out.println(combine);
    }

    public static List<List<Integer>> combine(int n, int k) {
        if (n < k) return null;

        List<List<Integer>> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        dfs(1, n, k, list1, list2);

        return list1;
    }

    private static void dfs(int idx, int n, int k, List<List<Integer>> list1, List<Integer> list2) {
        if (list2.size() == k){
            list1.add(new ArrayList<>(list2));
            return;
        }

        // 当条件是n - (k - list2.size()) + 1的时候，会起到剪枝的效果，减少很多不必要的操作
        for (int i = idx; i <= n - (k - list2.size()) + 1; i++) {
            list2.add(i);
            dfs(i + 1, n, k, list1, list2);
            list2.remove(list2.size() - 1);
        }
    }
}
