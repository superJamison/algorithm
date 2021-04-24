package com.jms;

import com.sun.deploy.panel.ITreeNode;

/**
 * 最大连续子序列和
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/21 20:46
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(getMaxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    private static int getMaxSubArray(int[] nums){
        return getMaxSubArray(nums, 0, nums.length);
    }

    // [begin, end)
    private static int getMaxSubArray(int[] nums, int begin, int end){
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;

        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(leftMax + rightMax,
                Math.max(
                        getMaxSubArray(nums, begin, mid),
                        getMaxSubArray(nums, mid, end)
                ));
    }

    private static int getMaxSubArray2(int[] nums){
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private static int getMaxSubArray1(int[] nums){
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
