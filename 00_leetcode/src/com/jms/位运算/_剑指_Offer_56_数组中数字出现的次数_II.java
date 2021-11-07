package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/19 15:15
 */
public class _剑指_Offer_56_数组中数字出现的次数_II {

    public int singleNumber(int[] nums) {
        int[] counts = new int[32];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                counts[j] += nums[i] & 1;
                nums[i] >>>= 1;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            counts[i] %= 3;
        }

        int result = 0;
        for (int i = 31; i >= 0; i--) {
            result <<= 1;
            result |= counts[i];
        }

        return result;
    }
}
