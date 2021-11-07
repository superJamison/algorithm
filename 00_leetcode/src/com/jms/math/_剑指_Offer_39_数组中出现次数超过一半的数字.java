package com.jms.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/20 14:45
 */
public class _剑指_Offer_39_数组中出现次数超过一半的数字 {

    // 摩尔投票法
    public int majorityElement(int[] nums) {
        int count = 1, x = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) x = nums[i];
            if (nums[i] == x) count++;
            else count--;

        }
        return x;
    }

    // hash表
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int half = nums.length / 2;
        for (int num : nums) {
            int orDefault = map.getOrDefault(num, 0) + 1;
            if (orDefault > half) return num;
            map.put(num, orDefault);
        }
        return -1;
    }

    // 排序
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
