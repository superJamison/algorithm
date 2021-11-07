package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/2 20:42
 */
public class _152_乘积最大子数组 {

    public static void main(String[] args) {
        _152_乘积最大子数组 v = new _152_乘积最大子数组();
        System.out.println(v.maxProduct(new int[]{3, -1, 4}));
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int max = nums[0], min = max, result = max;

        for (int i = 1; i < nums.length; i++) {
            int a = min * nums[i];
            int b = max * nums[i];
            max = Math.max(nums[i], Math.max(a, b));
            min = Math.min(nums[i], Math.min(a, b));
            result = Math.max(max, result);
        }

        return result;
    }
}
