package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/30 22:31
 */
public class _剑指_Offer_58_II_左旋转字符串 {
    public String reverseLeftWords(String s, int n) {
        n = n % s.length();

        String s1 = s.substring(0, n);
        String s2 = s.substring(n, s.length());
        return s2 + s1;

    }
}
