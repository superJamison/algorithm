package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/18 20:26
 *
 * https://leetcode-cn.com/problems/move-zeroes/
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class _283_移动零 {

    public static void main(String[] args) {
        moveZeroes(new int[]{0,1,0,3,12});
    }

    public static void moveZeroes(int[] nums) {
        int index = 0, current = 0;

        while (current < nums.length){
            if (nums[current] != 0){
                nums[index] = nums[current];
                index++;
            }
            current++;
        }

        while (index < nums.length){
            nums[index++] = 0;
        }
    }
}
