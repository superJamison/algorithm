package com.jms.排序算法;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/13 21:21
 */
public class _剑指_Offer_61_扑克牌中的顺子 {

    public static void main(String[] args) {
        _剑指_Offer_61_扑克牌中的顺子 v = new _剑指_Offer_61_扑克牌中的顺子();
        System.out.println(v.isStraight(new int[]{1, 2, 3, 4, 5}));
    }

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) count++;
            else if (nums[i] == nums[i + 1]) return false;
        }

        return nums[4] - nums[count] < 5;
    }
}
