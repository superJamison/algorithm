package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/21 16:17
 */
public class _剑指_Offer_14_剪绳子 {

    public int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

    public int cuttingRope1(int n) {
        if (n <= 3) return n - 1;

        int count = n / 3;
        int result = (int) Math.pow(3, count);
        int p = n % 3;
        if (p != 0){
            result = Math.max((result / 3) * (3 + p), result * p);
        }

        return result;
    }
}
