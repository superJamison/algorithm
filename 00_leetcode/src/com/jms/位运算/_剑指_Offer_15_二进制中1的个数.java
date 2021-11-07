package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/18 10:08
 */
public class _剑指_Offer_15_二进制中1的个数 {

    public static void main(String[] args) {
        _剑指_Offer_15_二进制中1的个数 v = new _剑指_Offer_15_二进制中1的个数();
        System.out.println(v.hammingWeight(-3));
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            n &= n - 1;
            count++;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) count++;
        }
        return count;
    }
}
