package com.jms.滑动窗口;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/21 22:40
 */
public class _剑指_Offer_59_I_滑动窗口的最大值2 {
    public static void main(String[] args) {
        int[] ints = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);

        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};

        int[] result = new int[nums.length - k + 1];

        int l = 0, r = 0;
        int maxIndex = 0;

        while (r < nums.length){
            if (r < k - 1){
                maxIndex = nums[r] > nums[maxIndex] ? r : maxIndex;
                r++;
            }else {
                maxIndex = nums[r] > nums[maxIndex] ? r : maxIndex;
                result[l] = nums[maxIndex];
                l++;
                // 重新计算最大值索引
                if (l - 1 != maxIndex){
                    maxIndex = nums[r] > nums[maxIndex] ? r : maxIndex;
                }else {
                    if (nums[r] > nums[maxIndex]){
                        maxIndex = r;
                    }else {
                        maxIndex = l;
                        for (int i = l; i <= r; i++) {
                            if (nums[i] > nums[maxIndex]){
                                maxIndex = i;
                            }
                        }
                    }
                }

                r++;
            }
        }

        return result;
    }
}
