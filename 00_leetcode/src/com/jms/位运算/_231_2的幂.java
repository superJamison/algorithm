package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/29 22:05
 */
public class _231_2的幂 {
    public static void main(String[] args) {
//        System.out.println(1 << 0);
        System.out.println(isPowerOfTwo(2));

        // 10000000000
        // 01111111111
        // 00000000000

        // 10000100000
        // 10000011111
        // 10000000000
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
//        return (n ^ (n-1)) == n - 1 + n;
//        return n > 0 && Integer.bitCount(n) == 1;
    }

    public static boolean isPowerOfTwo1(int n) {
        int i = 0;
        boolean flag = false;
        while (true){
            int temp = 1 << i;
            if (temp == n){
                flag = true;
                break;
            }else if (temp > n){
                break;
            }
            i++;
        }

        return flag;
    }
}
