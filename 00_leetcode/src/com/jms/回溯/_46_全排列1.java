package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列升级版
 * @author Jamison
 * @version 1.0
 * @date 2021/8/27 20:07
 * https://leetcode-cn.com/problems/permutations/
 */
public class _46_全排列1 {
    public static void main(String[] args) {
        _46_全排列 v = new _46_全排列();
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = v.permute(nums);

        System.out.println(result);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        dfs(0, nums, result);

        return result;
    }

    private void dfs(int idx, int[] nums, List<List<Integer>> result) {
        if (idx == nums.length){
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            dfs(idx + 1, nums, result);
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int idx, int i) {
        int temp = nums[idx];
        nums[idx] = nums[i];
        nums[i] = temp;
    }
}
