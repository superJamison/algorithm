package com.jms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/28 21:03
 */
public class _7_整数反转 {

    public static void main(String[] args) {
//        System.out.println(reverse(1534236469));
//        System.out.println();

//        System.out.println(Math.pow(-2, 31));
//        System.out.println(Math.pow(2, 31));
//        System.out.println(Integer.MAX_VALUE);

        int i = 2;

        i = i + 1;

        System.out.println(i);

    }

    public static int reverse(int x) {
        if (x > -10 && x < 10) return x;

        int result = 0;

        while (x != 0){
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) return 0;
            int i = x % 10;
            x /= 10;
            result = result * 10 + i;
        }

        return result;
    }

    public static int reverse1(int x) {
        if (x > -10 && x < 10) return x;

        List<Integer> list = new ArrayList<>();

        int temp = Math.abs(x);
        while (temp > 0){
            list.add(temp % 10);
            temp = temp / 10;
        }

        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(result);
            result += Math.pow(10, list.size() - 1 - i ) * list.get(i);
            if (result >= Integer.MAX_VALUE) return 0;
        }

        return x < 0 ? - result : result;
    }
}
