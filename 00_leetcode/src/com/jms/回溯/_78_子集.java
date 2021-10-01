package com.jms.回溯;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/11 20:25
 * https://leetcode-cn.com/problems/subsets/
 */
public class _78_子集 {
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});

        for (List<Integer> subset : subsets) {
            for (Integer integer : subset) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(list);
            return result;
        }

        dfs(0, nums, list, result);

        return result;
    }

    /**
     * 访问第i层，选中第i层的元素，与不选中第i层的元素
     */
    private static void dfs(int i, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (i == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[i]);
        dfs(i + 1, nums, list, result);
        // 回溯
        list.remove(list.size() - 1);
        dfs(i + 1, nums, list, result);
    }
}
