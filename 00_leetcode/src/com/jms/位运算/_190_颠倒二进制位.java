package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/30 21:16
 */
public class _190_颠倒二进制位 {

    public static void main(String[] args) {
        System.out.println(reverseBits(43261596));

//        System.out.println(Integer.toBinaryString(43261596));

        /**
         * 10100101000001111010011100
         *
         * 0001111010011100 0000001010010100
         *
         * 0001111010011100 0000001010010100
         * 1111111100000000 1111111100000000
         *
         *
         */
    }

    // c语言可以这样写，java会导致溢出
    public static int reverseBits(int n) {
        if (n == 0) return n;
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits3(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    public static int reverseBits2(int n) {
        if (n == 0) return n;

        return Integer.reverse(n);
    }

    public static int reverseBits1(int n) {
        if (n == 0) return n;

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>= 1;
        }

        return res;
    }
}
