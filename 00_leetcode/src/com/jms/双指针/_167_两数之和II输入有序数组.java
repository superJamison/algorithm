package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/18 20:39
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class _167_两数之和II输入有序数组 {

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);

        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (true){
            int sum = numbers[left] + numbers[right];
            if (sum == target) break;
            if (sum > target) {
                right--;
            }else{
                left++;
            }
        }

        return new int[]{left + 1, right + 1};
    }
}
