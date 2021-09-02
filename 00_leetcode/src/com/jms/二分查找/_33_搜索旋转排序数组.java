package com.jms.二分查找;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/31 21:11
 */
public class _33_搜索旋转排序数组 {
    public static void main(String[] args) {
//        int result = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        int result = search(new int[]{3, 1}, 1);
        System.out.println(result);
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) >> 1;

            if (target == nums[mid]) return mid;

            // 方法1
            if (nums[mid] < nums[right]){
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }else {
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;

            }

            // 方法2
//            if (nums[left] < nums[mid]){
//                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
//                else left = mid + 1;
//            }else {
//                if (mid + 1 < nums.length && nums[mid + 1] <= target && target <= nums[right]) left = mid + 1;
//                else right = mid - 1;
//            }
        }

        return -1;
    }
}
