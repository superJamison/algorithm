package com.jms.数组;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * @author Jamison
 * @version 1.0
 * @date 2021/5/16 23:04
 */
public class _421_数组中两个数的最大异或值 {

    public static int findMaximumXOR(int[] nums) {
        int max = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            // 从最高位试着找nums的前缀
            mask = mask | (1 << i);
            HashSet<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(mask & num);
            }
            //判断最大异或结果的当前位是否为1
            int temp=max|(1<<i);
            for (int prefix: set){
                if (set.contains(prefix^temp)) {
                    max=temp;
                }
            }
        }
        return max;
    }

    public static int findMaximumXOR1(int[] nums) {
        if (nums.length == 1){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(nums[i] ^ nums[j], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
//        System.out.println(Integer.toBinaryString(0 | (1 << 31)));
    }
}
