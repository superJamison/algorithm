package com.jms;

/**
 * 最长上升子序列的长度
 * dp[i] 是以nums[i] 结尾的最长上升子序列的长度
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/23 23:12
 */
public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 2, 2, 5, 1, 7, 101, 18}));
    }

    /**
     * 牌顶的方式
     */
    private static int lengthOfLIS(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        // 牌堆的数量
        int len = 0;
        // 牌顶数组
        int[] top = new int[nums.length];
        //遍历所有的牌
        for (int num : nums) {
            int begin = 0;
            int end = len;
            // 二分搜索查找num插入的牌堆索引
            while (begin < end){
                int mid = (begin + end) >> 1;
                if (num <= top[mid]){
                    end = mid;
                }else {
                    begin = mid + 1;
                }
            }
            // 覆盖牌顶
            top[begin] = num;
            // 检查是否要新建一个牌堆
            if (begin == len) len++;
        }
        return len;
    }

    /**
     * 动态规划
     */
    private static int lengthOfLIS1(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }



}
