package com.jms.二分查找;

import java.util.Random;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/4 16:13
 */
public class _367_有效的完全平方数 {

    public static void main(String[] args) {
        System.out.println((2 + 2) >> 1 + 1);
    }

    public boolean isPerfectSquare(int num) {
        int l = 0, r = num;
        while (l <= r){
            int mid = ((r - l) >> 1) + l;
            long x = (long) mid * mid;
            if (x < num){
                l = mid + 1;
            }else if (x > num){
                r = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }
}
