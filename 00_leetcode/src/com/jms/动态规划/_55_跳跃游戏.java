package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/15 21:06
 */
public class _55_跳跃游戏 {
    public static void main(String[] args) {
//        boolean result = canJump(new int[]{2, 3, 1, 1, 4});
//        boolean result = canJump(new int[]{3, 2, 1, 0, 4});
//        boolean result = canJump(new int[]{2, 0, 0});
//        boolean result = canJump(new int[]{3, 0, 8, 2, 0, 0, 1});
//        boolean result = canJump(new int[]{1,1,2,2,0,1,1});
        boolean result = canJump(new int[]{5,9,3,2,1,0,2,3,3,1,0,0});

        System.out.println(result);
    }

    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return true;

        int maxLength = nums[0];

        for (int i = 0; i < nums.length; i++) {
            // 最大的步数大于数组的长度，返回true
            if (maxLength >= nums.length - 1) return true;

            if (maxLength >= i){
                maxLength = Math.max(maxLength, i + nums[i]);
            }else {
                return false;
            }
        }

        return false;
    }

    public static boolean canJump4(int[] nums) {
        if (nums == null || nums.length == 0 || (nums.length > 1 && nums[0] == 0)) return false;
        int maxIndex = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && maxIndex <= i) return false;
            maxIndex = Math.max(maxIndex, nums[i] + i);
        }
        return true;
    }

    public static boolean canJump3(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        // 数组位于i位置是否可到达
        int i = 1;
        while (i < nums.length) {
            int prev = i - 1;
            if (nums[prev] == 0) return false;
            int index = 1;
            while (i++ < nums.length && index <= nums[prev]) {
                if (nums[prev + index] >= nums[prev] - index) break;
                index++;
            }
        }
        return true;
    }

    public static boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        // 数组位于i位置是否可到达
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int prev = i - 1;
            int count = 0;
            while (prev >= 0) {
                if (dp[prev] - count > 0) {
                    dp[i] = nums[i];
                    break;
                }
                prev--;
                count++;
            }
            if (count == i) {
                dp[i] = -1;
            }
        }
        return dp[nums.length - 1] != -1;
    }

    public static boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        // dp[i] 表示可以跳到i位置
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;

        for (int i = 1; i < dp.length; i++) {
            // 往回遍历看是否存在可以跳到第i个位置的点
            for (int j = i - 1; j >= 0; j--) {
                if (!dp[j]) continue;
                if (nums[j] < i - j) continue;
                dp[i] = true;
                break;
            }
        }

        return dp[nums.length - 1];
    }
}
