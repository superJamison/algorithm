package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/28 11:18
 */
public class _剑指_Offer_44_数字序列中某一位的数字 {

    public static void main(String[] args) {
        _剑指_Offer_44_数字序列中某一位的数字 v = new _剑指_Offer_44_数字序列中某一位的数字();
        System.out.println(v.findNthDigit(10));
    }

    public int findNthDigit(int n) {
        //  num:   0-9        10-99        100-999          1000-9999
        //count:   1*1*9      2*10*9       3*100*9          4*1000*9
        long count = 9;
        long a = 1, b = 1, c = 9;

        while (count < n){
            n -= count;
            a++;
            b *= 10;
            count = a * b * c;
        }
        long num = b + (n - 1) / a;
        return Long.toString(num).charAt((int) ((n - 1) % a)) - '0';
    }
}
