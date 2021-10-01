package com.jms.滑动窗口;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/6 17:39
 */
public class _209_长度最小的子数组 {
    public static void main(String[] args) {
        int result = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
//        int result = minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1});

        System.out.println(result);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null | nums.length == 0) return 0;

        int result = Integer.MAX_VALUE;
        int cur = 0, slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            cur += nums[fast];
            while (cur >= target){
                result = Math.min(result, fast - slow + 1);
                cur -= nums[slow];
                slow++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static int minSubArrayLen1(int target, int[] nums) {
        if (nums == null | nums.length == 0) return 0;

        int result = Integer.MAX_VALUE;
        int cur = 0, fast = 0;

        for (int slow = 0; slow < nums.length; slow++) {
            fast = slow;
            cur = 0;
            while (fast < nums.length && (cur += nums[fast]) < target) {
                fast++;
            }

            if (cur >= target) {
                result = Math.min(result, fast - slow + 1);
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
