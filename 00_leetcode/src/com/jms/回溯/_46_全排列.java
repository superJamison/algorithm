package com.jms.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/27 20:07
 * https://leetcode-cn.com/problems/permutations/
 */
public class _46_全排列 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = permute(nums);

        for (List<Integer> list : result) {
            for (Integer integer : list) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        List<Integer> list = new ArrayList<>();
        dfs(0, nums, list, result);

        return result;
    }

    private static void dfs(int idx, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (idx == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        for (int num : nums) {
            if (list.contains(num)) continue;

            list.add(num);
            dfs(idx + 1, nums, list, result);
            list.remove(list.size() - 1);
        }
    }
}
