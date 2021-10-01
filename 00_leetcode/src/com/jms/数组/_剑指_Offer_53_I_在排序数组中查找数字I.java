package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/1 21:31
 */
public class _剑指_Offer_53_I_在排序数组中查找数字I {

    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]){
                int count = 1;
                for (int j = i + 1; j < nums.length; j++) {
                    if (target != nums[j]) return count;
                    count++;
                }
                return count;
            }
        }
        return 0;
    }
}
