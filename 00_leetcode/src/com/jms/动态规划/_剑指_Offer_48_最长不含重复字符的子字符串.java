package com.jms.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/7 12:38
 */
public class _剑指_Offer_48_最长不含重复字符的子字符串 {
    public static void main(String[] args) {

    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int l = 0, r = 0;

        while (r < s.length()) {
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(l, map.get(s.charAt(r)));
            }
            map.put(s.charAt(r), r);
            max = Math.max(max, r - l);
            r++;
        }

        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int l = 0, r = 0;

        while (r < s.length()) {
            if (!map.containsKey(s.charAt(r))) {
                map.put(s.charAt(r), r);
                r++;
                max = Math.max(max, r - l);
            } else {
                while (l <= map.get(s.charAt(r))) {
                    map.remove(s.charAt(l));
                    l++;
                }
            }
        }

        return max;
    }
}
