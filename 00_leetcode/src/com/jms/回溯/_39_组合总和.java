package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/12 22:47
 */
public class _39_组合总和 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        combination(candidates, target, 0, new ArrayList<>(), result, 0);

        return result;
    }

    private static void combination(int[] candidates, int target, int index, List<Integer> list, List<List<Integer>> result, int curSum) {
        if (curSum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        int removeIndex = list.size();
        for (int i = index; i < candidates.length; i++) {
            if (curSum + candidates[i] > target) continue;
            list.add(candidates[i]);
            combination(candidates, target, i, list, result, curSum + candidates[i]);
            list.remove(removeIndex);
        }
    }
}
