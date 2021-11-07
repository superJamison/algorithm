package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/12 21:45
 */
public class _371_两整数之和 {

    public int getSum(int a, int b) {
        while (b != 0){
            int temp = (a & b) << 1;
            a = a ^ b;
            b = temp;
        }

        return a;
    }
}
