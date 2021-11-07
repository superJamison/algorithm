package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/18 11:17
 */
public class _476_数字的补数 {

    public static void main(String[] args) {
        _476_数字的补数 v = new _476_数字的补数();
        System.out.println(v.findComplement(5));
    }

    public int findComplement(int num) {
        int n = num;
        int index = 0;

        // 0^0=0  0^1=1  1^0=1  1^1=0

        while (n != 0){
            index++;
            n >>= 1;
        }
        for (int i = 0; i < index; i++) {
            num ^= 1 << i;
        }

        return num;
    }
}
