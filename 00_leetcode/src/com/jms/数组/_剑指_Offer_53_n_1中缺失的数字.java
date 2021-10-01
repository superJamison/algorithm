package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/1 21:39
 */
public class _剑指_Offer_53_n_1中缺失的数字 {

    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l <= r){
            int mid = (l + r) >> 1;

            if (nums[mid] == mid) l = mid + 1;
            else r = mid - 1;
        }

        return l;
    }

    public int missingNumber1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }

        return nums.length;
    }
}
