package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/2 16:24
 */
public class _201_数字范围按位与 {

    public int rangeBitwiseAnd(int left, int right) {
        while (left < right){
            right &= right - 1;
        }
        return right;
    }

    // 关键就是求left和right的共同的前缀
    public int rangeBitwiseAnd1(int left, int right) {
        int shift = 0;
        while (left < right){
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left <<= shift;
    }
}
