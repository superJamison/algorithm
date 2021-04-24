package com.jms;

/**
 * 阶乘问题
 * 1*2*3*4*5*6....*(n-1)*n=?
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/19 22:09
 */
public class TailCall {
    public static void main(String[] args) {
        System.out.println(facttorial(4));
        System.out.println(facttorial1(4));
    }

    private static int facttorial(int n){
        if (n <= 1) return 1;
        return n * facttorial(n - 1);
    }

    private static int facttorial1(int n){
        return facttorial(n, 1);
    }

    private static int facttorial(int n, int result) {
        if (n <= 1) return result;
        return facttorial(n - 1, result * n);
    }
}
