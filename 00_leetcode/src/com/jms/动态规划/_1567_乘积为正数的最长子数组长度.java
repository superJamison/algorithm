package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 10:26
 */
public class _1567_乘积为正数的最长子数组长度 {

    public static void main(String[] args) {
        _1567_乘积为正数的最长子数组长度 v = new _1567_乘积为正数的最长子数组长度();
        System.out.println(v.getMaxLen(new int[]{-16, 0, -5, 2, 2, -13, 11, 8}));
    }

    public int getMaxLen(int[] nums) {
        if (nums.length == 1) return nums[0] > 0 ? 1 : 0;

        int result = nums[0] > 0 ? 1 : 0;
        int max = result, min = nums[0] < 0 ? 1 : 0, cur = max;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max += 1;
                min = min > 0 ? min + 1 : 0;
            } else if (nums[i] < 0) {
                int oldMax = max;
                max = min > 0 ? min + 1 : 0;
                min = oldMax + 1;
            } else {
                max = 0;
                min = 0;
            }
            result = Math.max(result, max);
        }

        return result;
    }
}
