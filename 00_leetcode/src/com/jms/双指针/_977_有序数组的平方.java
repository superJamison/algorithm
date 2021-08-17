package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/17 16:52
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 */
public class _977_有序数组的平方 {

    public static void main(String[] args) {
//        int[] result = sortedSquares(new int[]{-4, -1, 0, 3, 10});
        int[] result = sortedSquares(new int[]{-7,-3,2,3,11});

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 双指针解有序数组的平方，2.0版本
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        if (nums.length == 0) return nums;

        int[] res = new int[nums.length];

        // 设定一个left，right
        int l = 0, r = nums.length - 1, i = r;

        // 设定一个指针指向当前未遍历过的数据的最小值
        while (l <= r){
            // 右边的绝对值比左边的大
            if (nums[r] * nums[r] > nums[l] * nums[l]){
                res[i] = nums[r] * nums[r--];
            }else {
                res[i] = nums[l] * nums[l++];
            }
            i--;
        }

        return res;
    }

    /**
     * 双指针解有序数组的平方，1.0版本
     * @param nums
     * @return
     */
    public static int[] sortedSquares1(int[] nums) {
        if (nums.length == 0) return nums;

        int[] result = new int[nums.length];

        // 设定一个left，right
        int left = 0, right = nums.length - 1, index = right;

        // 设定一个指针指向当前未遍历过的数据的最小值
        while (left <= right){
            // 右边的绝对值比左边的大
            if (Math.abs(nums[right]) > Math.abs(nums[left])){
                result[index] = (int) Math.pow(nums[right], 2);
                right--;
            }else {
                result[index] = (int) Math.pow(nums[left], 2);
                left++;
            }
            index--;
        }

        return result;
    }
}
