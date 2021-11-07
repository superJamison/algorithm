package com.jms.动态规划;

import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/28 22:29
 */
public class _198_打家劫舍 {
    public static void main(String[] args) {
//        int result = rob(new int[]{1, 2, 3, 1});
        int result = rob(new int[]{2,7,9,3,1});

        System.out.println(result);
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    public static int rob3(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int first = nums[0], temp;
        int second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            temp = second;
            second = Math.max(nums[i] + first, second);
            first = temp;
        }

        return second;
    }

    public static int rob2(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i % 2] = Math.max(nums[i] + dp[(i - 2) % 2], dp[(i - 1) % 2]);
        }

        return Math.max(dp[0], dp[1]);
    }

    public static int rob1(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }

        return dp[n];
    }
}
