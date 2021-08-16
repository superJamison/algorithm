package com.jms.数组;

import com.jms.utils.Asserts;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/16 11:20
 */
public class _35_搜索插入位置 {

    public static void main(String[] args) {
        Asserts.test(searchInsert(new int[]{1,3,5,6}, 5) == 2);
        Asserts.test(searchInsert(new int[]{1,3,5,6}, 3) == 1);
        Asserts.test(searchInsert(new int[]{1,3,5,6}, 2) == 1);
        Asserts.test(searchInsert(new int[]{1,3,5,6}, 7) == 4);
        Asserts.test(searchInsert(new int[]{1,3,5,6}, 0) == 0);
//        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
//        System.out.println(searchInsert(new int[]{1,3,5,6}, 3));
//        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
//        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
//        System.out.println(searchInsert(new int[]{1,3,5,6}, 0));
    }

    public static int searchInsert(int[] nums, int target) {

        int left = 0, right = nums.length - 1, mid = 0;
        boolean flag = true;

        while (left <= right){
            mid = left + ((right - left) >> 1);

            // 目标在左边
            if (target < nums[mid]){
                right = mid - 1;
                flag = true;
            }
            // 目标在右边
            else if (target > nums[mid]){
                left = mid + 1;
                flag = false;
            }else {
                return mid;
            }
        }
        return flag ? mid : mid + 1;
    }
}
