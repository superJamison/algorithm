package com.jms.分治;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/17 14:50
 */
public class _剑指_Offer_16_数值的整数次方 {

    public static void main(String[] args) {
        _剑指_Offer_16_数值的整数次方 v = new _剑指_Offer_16_数值的整数次方();
        System.out.println(v.myPow(2, -2));
    }

    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;

        double num = myPow(x, n / 2);
        return num * num * myPow(x, n % 2);
    }
}
