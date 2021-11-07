package com.jms.二分查找;

import java.sql.SQLOutput;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/10 22:44
 */
public class _441_排列硬币 {

    public static void main(String[] args) {
        _441_排列硬币 v = new _441_排列硬币();
        System.out.println(v.arrangeCoins(1804289383));
    }

    public int arrangeCoins(int n) {
        return (((int)Math.sqrt((long) 8 * n + 1) - 1) >> 1);
    }

    public int arrangeCoins1(int n) {
        if (n == Integer.MAX_VALUE) return 65535;
        int row = 0, count = 0;

        while (count < n){
            row++;
            count += row;
        }
        if (count == n) return row;
        else return row - 1;
    }
}
