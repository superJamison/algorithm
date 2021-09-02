package com.jms.二分查找;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/31 20:34
 */
public class _34_在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
//        int[] result = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
        int[] result = searchRange(new int[]{1,2,3,3,3,3,4,5,9}, 3);
//        int[] result = searchRange(new int[]{1}, 1);

        for (int anInt : result) {
            System.out.println(anInt);
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int left = 0, right = nums.length - 1;

        int[] result = null;

        while (left <= right){
            int mid = (left + right) >> 1;

            if (target == nums[mid] ) {
                result = new int[]{mid, mid};

                while (--mid >= 0 && nums[mid] == target) result[0] = mid;
                mid = result[1];
                while (++mid < nums.length && nums[mid] == target) result[1] = mid;
                break;
            }else if (target < nums[mid]) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        if (result == null){
            result = new int[]{-1, -1};
        }

        return result;
    }
}
