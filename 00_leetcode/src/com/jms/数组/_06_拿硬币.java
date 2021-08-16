package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/5/16 22:52
 */
public class _06_拿硬币 {

    public static int minCount(int[] coins) {
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            count += (coins[i] & 1) > 0 ? (coins[i] >> 1) + 1 : coins[i] >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(minCount(new int[]{4, 2, 1}));
    }
}
