package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/26 10:47
 *
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 */
public class _485_最大连续1的个数 {

    public static void main(String[] args) {
        int[] ints = new int[]{1,1,0,1,1,1};
        int maxConsecutiveOnes = findMaxConsecutiveOnes(ints);
        System.out.println(maxConsecutiveOnes);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cur = 0;
        for (int x : nums) {
            cur = x == 0 ? 0 : cur + 1;
            max = Math.max(max, cur);
        }
        return max;
    }

    public static int findMaxConsecutiveOnes1(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1){
                count++;
            }else {
                Math.max(count, max);
                count = 0;
            }
        }

        Math.max(count, max);

        return max;
    }
}
