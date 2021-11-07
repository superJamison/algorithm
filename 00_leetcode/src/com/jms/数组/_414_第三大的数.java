package com.jms.数组;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/6 11:17
 */
public class _414_第三大的数 {

    public int thirdMax(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        long a = Long.MIN_VALUE;
        long b = Long.MIN_VALUE;
        long c = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > c) {
                a = b;
                b = c;
                c = nums[i];
            } else if (nums[i] > b && nums[i] < c) {
                a = b;
                b = nums[i];
            } else if (nums[i] >= a && nums[i] < b) {
                a = nums[i];
            }
        }

        return (int) (a == Long.MIN_VALUE ? c : a);
    }

    public int thirdMax1(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        long a = Long.MIN_VALUE;
        long b = Long.MIN_VALUE;
        long c = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > c) {
                a = b;
                b = c;
                c = nums[i];
            } else if (nums[i] > b && nums[i] < c) {
                a = b;
                b = nums[i];
            } else if (nums[i] >= a && nums[i] < b) {
                a = nums[i];
            }
        }

        return (int) (a == Long.MIN_VALUE ? c : a);
    }
}
