package com.jms.滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/5 20:55
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/
 *
 * *******************************************************
 */
public class _438_找到字符串中所有字母异位词 {
    public static void main(String[] args) {
        List<Integer> result = findAnagrams("baa", "aa");

        for (Integer integer : result) {
            System.out.print(integer + "  ");
        }
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        int[] sInts = new int[26];
        int[] pInts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pInts[p.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            if (pInts[s.charAt(i) - 'a'] > 0) {
                boolean flag = true;
                int pLength = p.length();
                while (pLength > 0) {
                    if (i < s.length()) {
                        sInts[s.charAt(i++) - 'a']++;
                    } else {
                        break;
                    }
                    pLength--;
                }

                pLength = 1;
                while (pLength <= p.length()) {
                    if (sInts[s.charAt(i - pLength) - 'a'] != pInts[s.charAt(i - pLength) - 'a']) {
                        flag = false;
                    }
                    pLength++;
                }

                if (flag) {
                    result.add(i - p.length());
                }

                while (pLength > 1) {
                    sInts[s.charAt(--i) - 'a'] = 0;
                    pLength--;
                }
            }
        }

        return result;
    }
}
