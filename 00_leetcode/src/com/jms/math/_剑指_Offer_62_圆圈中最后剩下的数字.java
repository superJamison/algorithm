package com.jms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/21 19:40
 */
public class _剑指_Offer_62_圆圈中最后剩下的数字 {

    public static void main(String[] args) {
        _剑指_Offer_62_圆圈中最后剩下的数字 v = new _剑指_Offer_62_圆圈中最后剩下的数字();
        System.out.println(v.lastRemaining(5, 3));
    }

    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = 0;
        while (n > 1){
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }

        return list.get(0);
    }
}
