package com.jms.math;

/**
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * @author Jamison
 * @version 1.0
 * @date 2021/5/30 22:52
 */
public class _64_求1加到n的和 {

    public static void main(String[] args) {
        System.out.println(5 << 2);
    }

    public int sumNums(int n) {
        boolean b = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    int sum = 0;
    public int sumNums5(int n) {
        boolean b = n > 1 && sumNums5(n - 1) > 0;
        sum += n;
        return sum;
    }

    public static int sumNums4(int n){
//        (n * (n + 1)) / 2
        return (n * n + n) >> 1;
    }

    public static int sumNums3(int n) {
        return (n * n + n) >> 1;
    }

    // 使用了乘除法
    public static int sumNums2(int n) {
        return (n * (n + 1)) / 2;
    }

    // 使用了for循环
    public static int sumNums1(int n) {
        int sum = 0;
        for (int i = n; i > 0; i--) {
            sum += i;
        }
        return sum;
    }
}
