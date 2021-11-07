package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/31 15:02
 */
public class _剑指_Offer_II_003_前n个数字二进制中1的个数 {

    public static void main(String[] args) {
        _剑指_Offer_II_003_前n个数字二进制中1的个数 v = new _剑指_Offer_II_003_前n个数字二进制中1的个数();
        int[] ints = v.countBits(5);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public int[] countBits(int n) {
        if (n == 0) return new int[]{0};
        if (n == 1) return new int[]{0, 1};

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        // 0 0 0
        // 1 1 1
        // 2 1 10
        // 3 2 11
        // 4 1 100
        // 5 2 101
        // 6 2 110
        // 7 3 111
        // 8 1 1000
        // 9 2 1001
        //10 2 1010
        //11 3 1011
        //12 2 1100
        //13 3 1101
        //14 3 1110
        //15 4 1111
        //16 1 10000
        int index = 1;
        for (int i = 2; i <= n; i++) {
            if (i == (1 << index)){
                dp[i] = 1;
                index++;
            }else {
                dp[i] = dp[i - (1 << (index - 1))] + 1;
            }
        }

        return dp;
    }
}
