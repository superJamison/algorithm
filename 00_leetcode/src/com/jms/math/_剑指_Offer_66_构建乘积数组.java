package com.jms.math;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/20 15:26
 */
public class _剑指_Offer_66_构建乘积数组 {

    // 求上三角和下三角
    public int[] constructArr(int[] a) {
        int[] result = new int[a.length];
        int l = result.length;
        if (l == 0) return new int[0];
        result[0] = 1;

        int temp = 1;
        for (int i = 1; i < a.length; i++) {
            result[i] = result[i - 1] * a[i];
        }

        for (int i = a.length - 2; i >= 0; i--) {
            temp *= a[i + 1];
            result[i] *= temp;
        }

        return result;
    }

    // o(n^2)
    public int[] constructArr1(int[] a) {
        int[] result = new int[a.length];
        for (int i = 0; i < result.length; i++) result[i] = 1;


        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) continue;
            for (int j = 0; j < result.length; j++) {
                if (i != j) result[j] *= a[i];
            }
        }

        return result;
    }
}
