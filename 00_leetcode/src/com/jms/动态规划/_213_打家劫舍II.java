package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/14 21:03
 */
public class _213_打家劫舍II {
    public static void main(String[] args) {
        int result = rob(new int[]{1,2,3,1});

        System.out.println(result);
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        if (n == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);

        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1)) ;
    }

    private static int robRange(int[] nums, int start, int end) {
        int first = nums[start], temp;
        int second = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            temp = second;
            second = Math.max(nums[i] + first, second);
            first = temp;
        }

        return second;
    }
}
