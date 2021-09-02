package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/29 22:37
 */
public class _191_位1的个数 {
    public static void main(String[] args) {
        hammingWeight(1);
    }

    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
