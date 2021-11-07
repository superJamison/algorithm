package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/2 17:47
 */
public class _918_环形子数组的最大和 {

    public static void main(String[] args) {
        _918_环形子数组的最大和 v = new _918_环形子数组的最大和();
        System.out.println(v.maxSubarraySumCircular(new int[]{-2, -3, -1}));
    }

    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 1、没有跨越，计算最大可能和
        int max = nums[0], maxSum = max;
        for (int i = 1; i < nums.length; i++) {
            maxSum = Math.max(maxSum + nums[i], nums[i]);
            max = Math.max(max, maxSum);
        }
        if (max < 0) return max;

        // 2、有跨越，计算整个数组和 - 最小可能和
        int min = nums[0], minSum = min, total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            total += nums[i];
            minSum = Math.min(minSum + nums[i], nums[i]);
            min = Math.min(min, minSum);
        }

        return Math.max(max, total - min);
    }
}
