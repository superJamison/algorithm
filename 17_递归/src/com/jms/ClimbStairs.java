package com.jms;

/**
 * n阶台阶
 * 思路：假设n阶台阶有f（n）种走法，第一步有两种走法
 * 若第一步是走一步，那么还剩n - 1个台阶，有f（n - 1）种走法
 * 若第一步是走二步，那么还剩n - 2个台阶，有f（n - 2）种走法
 * 所以f（n） = f（n - 1） + f（n - 2）
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/18 21:23
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
        System.out.println(climbStairs1(4));
    }

    private static int climbStairs(int n){
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    private static int climbStairs1(int n){
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }

}
