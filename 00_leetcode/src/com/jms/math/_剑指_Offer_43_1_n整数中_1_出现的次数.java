package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/28 16:06
 */
public class _剑指_Offer_43_1_n整数中_1_出现的次数 {

    // n=23434，求十位上的1的个数：
    // ...........
    public int countDigitOne(int n) {
        long mulk = 1;
        int result = 0;
        while (mulk <= n) {
            result += (n / (mulk * 10)) * mulk + Math.min(Math.max(0,n % (mulk * 10) - mulk + 1), mulk);
            mulk *= 10;
        }
        return result;
    }
}
