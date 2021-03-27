package com.jms;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/17 22:23
 */
public class Main {

    /**
     * 时间复杂度是2^n
     * @param n
     * @return
     */
    public static int fib1(int n){
        if (n <= 1){
            return n;
        }
        return fib1(n - 2) + fib1(n - 1);
    }

    /**
     * 时间复杂度是n
     * @param n
     * @return
     */
    public static int fib2(int n){
        if (n <= 1){
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /**
     * 时间复杂度是n
     * @param n
     * @return
     */
    public static int fib3(int n){
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        while (n-- > 1){
            second += first;
            first = second - first;
        }
        return second;
    }

    /**
     * 利用线性代数的解法
     * @param n
     * @return
     */
    public static int fib4(int n) {
        double c = Math.sqrt(5);//根号5
        return (int)((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }


    public static void main(String[] args) {
        // 0 1 1 2 3 5 8 13
        int n = 45;
        long l = System.currentTimeMillis();
        System.out.println(fib4(n));
        long l1 = System.currentTimeMillis();
        System.out.println(fib2(n));
        long l2 = System.currentTimeMillis();
        System.out.println(l1 - l);
        System.out.println(l2 - l1);
    }
}
