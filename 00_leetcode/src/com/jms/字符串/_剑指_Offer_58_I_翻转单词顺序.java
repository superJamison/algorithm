package com.jms.字符串;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/10 22:21
 */
public class _剑指_Offer_58_I_翻转单词顺序 {

    public static void main(String[] args) {
        _剑指_Offer_58_I_翻转单词顺序 v = new _剑指_Offer_58_I_翻转单词顺序();
        v.reverseWords("the sky is blue");
    }

    public String reverseWords(String s) {
        s = s.trim();

        StringBuilder stringBuilder = new StringBuilder();

        int l = s.length() - 1, r = l;

        while (l >= 0){
            while (l >= 0 && s.charAt(l) != ' ') l--;
            stringBuilder.append(s.substring(l + 1, r + 1) + " ");
            while (l >= 0 && s.charAt(l) == ' ') l--;
            r = l;
        }

        return stringBuilder.toString().trim();
    }

    public String reverseWords1(String s) {
        String[] split = s.trim().split("\\s+");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = split.length - 1; i >= 0; i--) {
            stringBuilder.append(split[i] + " ");
        }

        return stringBuilder.toString().trim();
    }
}
