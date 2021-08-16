package com.jms.字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/jewels-and-stones/
 * @author Jamison
 * @version 1.0
 * @date 2021/5/30 16:10
 */
public class _771_宝石与石头 {
    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAbbbbbB"));
    }

    /**
     * @param jewels 宝石的类型
     * @param stones 石头的类型
     * @return 返回stones中宝石的个数
     */
    public static int numJewelsInStones1(String jewels, String stones) {
        if (jewels == null || jewels.length() == 0) return 0;
        if (stones == null || stones.length() == 0) return 0;
        int count = 0;
        int[] types = new int[256];
        for (int i = 0; i < jewels.length(); i++) {
            types[jewels.charAt(i)] = 1;
        }
        for (int i = 0; i < stones.length(); i++) {
            count += types[stones.charAt(i)];
        }
        return count;
    }

    public static int numJewelsInStones(String jewels, String stones) {
        if (jewels == null || jewels.length() == 0) return 0;
        if (stones == null || stones.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }

        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))){
                count++;
            }
        }
        return count;
    }
}
