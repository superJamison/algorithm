package com.jms.位运算;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/30 17:03
 */
public class _260_只出现一次的数字_III {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(-8));
        System.out.println(Integer.toBinaryString(8 & -8));
    }

    public int[] singleNumber(int[] nums) {
        int a = 0;
        for (int num : nums) {
            a ^= num;
        }
        int b = a & -a;

        int c = 0, d = 0;
        for (int num : nums) {
            if ((num & b) == 0){
                c ^= num;
            }else {
                d ^= num;
            }
        }
        return new int[]{c, d};
    }

    public int[] singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num,0) + 1);
        }

        int[] reslut = new int[2];
        int index = 0;
        for (int num : nums) {
            if (map.get(num) == 1) {
                reslut[index++] = num;
                if (index == 2) break;
            }
        }

        return reslut;
    }
}
