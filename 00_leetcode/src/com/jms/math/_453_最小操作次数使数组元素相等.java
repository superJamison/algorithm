package com.jms.math;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/20 16:03
 */
public class _453_最小操作次数使数组元素相等 {

    public static void main(String[] args) {
        _453_最小操作次数使数组元素相等 v = new _453_最小操作次数使数组元素相等();
        System.out.println(v.minMoves(new int[]{1, 2, 3}));
    }

    public int minMoves(int[] nums) {
        int asInt = Arrays.stream(nums).min().getAsInt();
        int count = 0;

        for (int num : nums) {
            count += num - asInt;
        }
        return count;
    }

    // 超时
    public int minMoves1(int[] nums) {
        if (nums.length == 1) return 0;
        int count = 0;

        int max = nums[0], maxIndex = 0;
        boolean f = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) f = false;
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        if (f) return 0;

        int newMax = max, newMaxIndex = maxIndex;

        while (true) {
            boolean flag = false;
            for (int i = 0; i < nums.length; i++) {
                if (i != maxIndex) {
                    nums[i] += 1;
                    if (nums[i] > max) {
                        newMax = nums[i];
                        newMaxIndex = i;
                    }
                }
                if (nums[i] != max) flag = true;
            }
            if (max != newMax) {
                flag = true;
                max = newMax;
                maxIndex = newMaxIndex;
            }
            count++;
            if (!flag) break;
        }
        return count;
    }
}
