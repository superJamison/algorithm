package com.jms.滑动窗口;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/6 16:53
 */
public class _713_乘积小于K的子数组 {

    public static void main(String[] args) {
        int result = numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
//        int result = numSubarrayProductLessThanK(new int[]{1,2,3}, 0);
        System.out.println(result);
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        if (nums == null || nums.length == 0) return 0;

        int result = 0, slow = 0, cur = 1;

        for (int fast = 0; fast < nums.length; fast++) {
            cur *= nums[fast];
            while (cur >= k){
                cur /= nums[slow];
                slow++;
            }
            result += fast - slow + 1;
        }

        return result;
    }

    public static int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k <= 1) return 0;
        if (nums == null || nums.length == 0) return 0;

        int result = 0, slow = 0, fast;

        while (slow < nums.length){
            fast = slow;
            int temp = 1;
            while (fast < nums.length && (temp *= nums[fast]) < k){
                result++;
                fast++;
            }
            slow++;
        }

        return result;
    }
}
