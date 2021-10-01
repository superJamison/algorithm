package com.jms.数组;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/1 20:51
 */
public class _剑指_Offer_03_数组中重复的数字 {

    public static void main(String[] args) {
        int repeatNumber = findRepeatNumber(new int[]{3, 4, 2, 0, 0, 1});

        System.out.println(repeatNumber);
    }

    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) continue;
            if (nums[nums[i]] == nums[i]) return nums[i];
            if (nums[nums[nums[i]]] == nums[nums[i]]) return nums[nums[i]];
            swap2Element(nums, nums[i], i);
        }

        return -1;
    }

    private static void swap2Element(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public int findRepeatNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.contains(num)){
                set.add(num);
            }else {
                return num;
            }
        }

        return -1;
    }
}
