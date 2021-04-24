package com.jms;

import com.jms.util.Times;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 斐波那契
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/18 20:51
 */
public class Fib {

    public static void main(String[] args) {
        int n = 10; //1 111 111 111 111 111
//        System.out.println(Math.pow(2, 16));
//        Times.test("fib0", () -> {
//            System.out.println(fib0(n));
//        });

//        Times.test("fib1", () -> {
//            System.out.println(fib1(n));
//        });
//
//        Times.test("fib2", () -> {
//            System.out.println(fib2(n));
//        });
//
//        Times.test("fib3", () -> {
//            System.out.println(fib3(n));
//        });
//
        Times.test("fib4", () -> {
            System.out.println(fib4(n));
        });

        Times.test("fib5", () -> {
            System.out.println(fib5(n));
        });


    }

    private static int fib0(int n){
        if (n <= 1) return n;
        return fib0(n - 1) + fib0(n - 2);
    }

    private static int fib1(int n){
        if (n <= 2) return 1;
        int[] ints = new int[n + 1];
        ints[1] = ints[2] = 1;
        return fib1(n, ints);
    }

    private static int fib1(int n, int[] ints){
        if (ints[n] == 0){
            ints[n] = fib1(n - 1, ints) + fib1(n - 2, ints);
        }
        return ints[n];
    }

    private static int fib2(int n){
        if (n <= 2) return 1;
        int[] ints = new int[n + 1];
        ints[1] = ints[2] = 1;
        for (int i = 3; i <= n; i++) {
            ints[i] = ints[i - 1] + ints[i - 2];
        }
        return ints[n];
    }

    private static int fib3(int n){
        if (n <= 2) return 1;
        int[] ints = new int[2];
        ints[0] = ints[1] = 1;
        for (int i = 3; i <= n; i++) {
            ints[i & 1] = ints[(i - 1) & 1] + ints[(i - 2) & 1];
        }
        return ints[n & 1];
    }

    private static int fib4(int n){
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }

    //转化为尾调用
    private static int fib5(int n){
        return fib(n, 1, 1);
    }

    private static int fib(int n, int first, int second) {
        if (n <= 1) return first;
        return fib(n - 1, second, first + second);
    }


}
