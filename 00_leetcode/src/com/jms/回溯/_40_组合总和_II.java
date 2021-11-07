package com.jms.回溯;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/4 20:02
 */
public class _40_组合总和_II {

    public static void main(String[] args) {
        _40_组合总和_II v = new _40_组合总和_II();
        List<List<Integer>> lists = v.combinationSum2(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                30);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        dfs(candidates, target, result, 0, new ArrayList<>());

        return result;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> result, int index, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int j = index; j < candidates.length; j++) {
            if (candidates[j] <= target){
                if (j > index && candidates[j] == candidates[j - 1]) continue;
                list.add(candidates[j]);
                dfs(candidates, target - candidates[j],result,j + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

}
