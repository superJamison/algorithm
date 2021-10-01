package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/17 23:10
 */
public class _45_跳跃游戏II {
    public static void main(String[] args) {
        int jump = jump(new int[]{2, 3, 1, 1, 4});
//        int jump = jump(new int[]{2,3,0,1,4});

        System.out.println();
        System.out.println(jump);
    }

    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        // dp[i]表示走到i位置上一步的最远距离
        int[] dp = new int[nums.length];
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int jump = nums[i] + i;
            if (jump < dp.length) {
                dp[jump] = dp[jump] == -1 ? i : dp[jump];
            }else {
                dp[dp.length - 1] = dp[dp.length - 1] == -1 ? i : dp[dp.length - 1];
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "  ");
        }

        int index = dp.length - 1;
        while (index > 0) {
            index = dp[index];
            count++;
        }

        return count;
    }

    public static int jump1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int index = nums.length - 1;
        int count = 0;

        // index到第一个位置就不需要往前找了
        while (index > 0) {
            // 找到跳到index位置最远的距离
            for (int i = 0; i < index; i++) {
                if (nums[i] + i >= index) {
                    count++;
                    index = i;
                    break;
                }
            }
        }

        return count;
    }
}
