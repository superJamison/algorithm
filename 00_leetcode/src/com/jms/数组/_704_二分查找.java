package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/16 8:58
 * https://leetcode-cn.com/problems/binary-search/
 */
public class _704_二分查找 {

    public static void main(String[] args) {
//        int index = search(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        int index = search(new int[]{-1,0,3,5,9,12}, 2);
        System.out.println(index);
    }

    /**
     * 二分查找 [0, nums.length)
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0, mid, right = nums.length - 1;

        while (left <= right){
            mid = (left + right) >> 1;
            if (target == nums[mid]) return mid;
            else if (target < nums[mid]) right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }

    public int searchxxx(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) return pivot;
            if (target < nums[pivot]) right = pivot - 1;
            else left = pivot + 1;
        }
        return -1;
    }

    /**
     * 二分查找 [0, nums.length) 2.0版本
     * @param nums
     * @param target
     * @return
     */
    public static int search2(int[] nums, int target) {
        if (nums.length == 0) return -1;

        int left = 0, mid = 0, right = nums.length, index = -1;

        while (true){
            mid = (left + right) / 2;
            // 左边
            if (target < nums[mid]){
                right = mid;
            }
            // 右边
            else if (target > nums[mid]){
                left = mid + 1;
            }else {
                index = mid;
                break;
            }
        }

        return index;
    }

    /**
     * 二分查找 [0, nums.length) 1.0版本
     * @param nums
     * @param target
     * @return
     */
    public static int search1(int[] nums, int target) {
        if (nums.length == 0) return -1;

        int left = 0;
        int right = nums.length;
        int mid = 0;
        int index = -1;

        while (true){
            mid = (left + right) / 2;
            if (target < nums[mid]){ // 左边
                right = mid;
                continue;
            }else if (target > nums[mid]){ // 右边
                left = mid + 1;
                continue;
            }else {
                index = mid;
                break;
            }
        }

        return index;
    }
}
