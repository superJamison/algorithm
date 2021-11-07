package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/19 14:54
 */
public class _剑指_Offer_56_数组中数字出现的次数 {

    public int[] singleNumbers(int[] nums) {
        int temp = 0;
        for (int num : nums) temp ^= num;

        int index = 1;
        while ((temp & index) == 0) index <<= 1;

        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & index) == 0) x ^= num;
            else y ^= num;
        }

        return new int[]{x, y};
    }
}
