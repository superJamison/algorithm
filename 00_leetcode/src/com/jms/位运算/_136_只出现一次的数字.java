package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/30 22:18
 */
public class _136_只出现一次的数字 {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{4,1,2,1,2}));
    }

    public static int singleNumber(int[] nums) {
        int single = 0;

        for (int num : nums) {
            single ^=  num;
        }

        return single;
    }
}
