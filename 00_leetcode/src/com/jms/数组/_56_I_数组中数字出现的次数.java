package com.jms.数组;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 * @author Jamison
 * @version 1.0
 * @date 2021/5/30 16:47
 */
public class _56_I_数组中数字出现的次数 {

    public static void main(String[] args) {
        System.out.println(singleNumbers(new int[]{4, 1, 4}));
    }

    public static int singleNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
