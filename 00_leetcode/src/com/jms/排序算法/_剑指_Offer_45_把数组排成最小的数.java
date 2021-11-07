package com.jms.排序算法;

import java.time.Year;
import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/13 20:26
 */
public class _剑指_Offer_45_把数组排成最小的数 {

    public static void main(String[] args) {
        _剑指_Offer_45_把数组排成最小的数 v = new _剑指_Offer_45_把数组排成最小的数();

        System.out.println(v.minNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";

        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs,(x, y) -> (x + y).compareTo(y + x));
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
