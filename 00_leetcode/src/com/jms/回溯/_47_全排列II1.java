package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/27 22:16
 */
public class _47_全排列II1 {

    public static void main(String[] args) {
        _47_全排列II1 v = new _47_全排列II1();
//        int[] nums = new int[]{1,2,3};
//        int[] nums = new int[]{1, 1, 3};
        int[] nums = new int[]{2, 2, 1, 1};
        List<List<Integer>> result = v.permuteUnique(nums);

        System.out.println(result);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        dfs(0, nums, result);

        return result;
    }

    private void dfs(int idx, int[] nums, List<List<Integer>> result) {
        if (idx == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            if (isRepeat(nums, idx, i)) continue;
            swap(nums, idx, i);
            dfs(idx + 1, nums, result);
            swap(nums, idx, i);
        }
    }

    private boolean isRepeat(int[] nums, int idx, int i) {
        for (int j = idx; j < i; j++) {
            if (nums[i] == nums[j]) return true;
        }
        return false;
    }

    private void swap(int[] nums, int idx, int i) {
        int temp = nums[idx];
        nums[idx] = nums[i];
        nums[i] = temp;
    }
}
