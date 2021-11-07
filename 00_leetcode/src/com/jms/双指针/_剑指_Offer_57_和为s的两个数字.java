package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/10 21:56
 */
public class _剑指_Offer_57_和为s的两个数字 {

    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{};

        int l = 0, r = nums.length - 1;
        int temp;

        while (l < r){
            temp = nums[l] + nums[r];
            if (temp == target) return new int[]{nums[l], nums[r]};
            else if (temp > target){
                r--;
            }else {
                l++;
            }
        }

        return new int[]{};
    }

}
