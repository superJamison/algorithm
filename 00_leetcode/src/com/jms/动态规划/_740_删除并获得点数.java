package com.jms.动态规划;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/31 15:40
 */
public class _740_删除并获得点数 {

    public static void main(String[] args) {
        _740_删除并获得点数 v = new _740_删除并获得点数();
        System.out.println(v.deleteAndEarn(new int[]{4,10,10,8,1,4,10,9,7,6}));
    }

    // 转化为打家劫舍问题
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int max = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
        }

        // 保存数值为i的个数
        int[] counts = new int[max + 1];
        for (int i = 0; i < n; i++) {
            counts[nums[i]] += 1;
        }

        int first = counts[1], second = Math.max(first, counts[2] * 2), temp;
        for (int i = 3; i < counts.length; i++) {
            temp = second;
            second = Math.max(second, first + i * counts[i]);
            first = temp;
        }

        return second;
    }

    // 排序+动态规划
    public int deleteAndEarn1(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        Arrays.sort(nums);

        int[] dp = new int[n];
        dp[0] = nums[0];

        int prev = 0, grandFather = 0, max = dp[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]){
                int index = i, count = 0;
                while (index >= 0 && nums[index] == nums[i]){
                    count += nums[index--];
                }
                int oldCount = 0;
                if (index >= 0 && nums[index] + 1 == nums[index + 1]){
                    oldCount = dp[index];
                    int num = nums[index];
                    while (index >= 0 && num == nums[index]){
                        index--;
                    }
                }
                dp[i] = Math.max(oldCount, (index >= 0 ? dp[index] : 0) + count);
            }else if (nums[i] == nums[i - 1] + 1){
                int index = i - 1;
                while (index >= 0 && nums[index] == nums[i - 1]){
                    index--;
                }
                dp[i] = Math.max((index == -1 ? 0 : dp[index]) + nums[i], dp[i - 1]);
                prev = dp[i - 1];
            }else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
