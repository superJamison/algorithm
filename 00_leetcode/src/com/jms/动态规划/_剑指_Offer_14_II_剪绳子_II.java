package com.jms.动态规划;

import java.math.BigInteger;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/28 10:31
 */
public class _剑指_Offer_14_II_剪绳子_II {

    public static void main(String[] args) {
        _剑指_Offer_14_II_剪绳子_II v = new _剑指_Offer_14_II_剪绳子_II();
        System.out.println(v.cuttingRope(555));
    }

    // 2 <= n <= 1000
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;

        long result = 1;
        int i = b == 1 ? 1 : 0;
        while (a != i){
            result *= 3;
            result %= 1000000007;
            a--;
        }

        if (b == 1) return (int) (result * 4 % 1000000007);
        if (b == 2) return (int) (result * 2 % 1000000007);
        return (int) (result % 1000000007);
    }
}
