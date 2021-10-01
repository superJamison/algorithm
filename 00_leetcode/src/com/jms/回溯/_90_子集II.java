package com.jms.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/11 20:25
 * https://leetcode-cn.com/problems/subsets-ii/submissions/
 *
 * 在_78_子集的基础上改进，使得在同一层不出现重复的情况
 */
public class _90_子集II {
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsetsWithDup(new int[]{1, 2, 2});

        for (List<Integer> subset : subsets) {
            for (Integer integer : subset) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(list);
            return result;
        }
        // 要去重就必须要先排好序
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];

        dfs(0, nums, list, result, used);

        return result;
    }

    private static void dfs(int i, int[] nums, List<Integer> list, List<List<Integer>> result, boolean[] used) {
        if (i == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // 如果当前元素和前一个元素相同，而且前一个元素没有被访问，说明前一个相同的元素在当前层已经被用过了，直接跳过这一层，访问下一层
        if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
            dfs(i + 1, nums, list, result, used);
            return;
        }

        list.add(nums[i]);
        // 标记为使用过了
        used[i] = true;
        dfs(i + 1, nums, list, result, used);
        list.remove(list.size() - 1);
        // 回溯
        used[i] = false;
        dfs(i + 1, nums, list, result, used);
    }
}
