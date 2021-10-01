package com.jms.滑动窗口;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/21 22:40
 */
public class _剑指_Offer_59_I_滑动窗口的最大值 {
    public static void main(String[] args) {
        int[] ints = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);

        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};

        int[] result = new int[nums.length - k + 1];

        int maxIndex = 0;

        for (int i = 1; i < k; i++) {
            if (nums[i] >= nums[maxIndex]) maxIndex = i;
        }

        result[0] = nums[maxIndex];

        for (int i = k; i < nums.length; i++) {
            // 重新计算maxIndex
            if (nums[i] >= nums[maxIndex]){
                maxIndex = i;
            }else if (maxIndex == i - k){
                // 前面一轮窗口的最大值的位置是第一个位置，滑动后已经被移出的窗口范围
                maxIndex = i - k + 1;
                for (int j = i - k + 2; j <= i; j++) {
                    if (nums[j] >= nums[maxIndex]) maxIndex = j;
                }
            }
            result[i - k + 1] = nums[maxIndex];
        }

        return result;
    }
}
