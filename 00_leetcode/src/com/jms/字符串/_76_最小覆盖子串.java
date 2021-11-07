package com.jms.字符串;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/10 23:54
 */
public class _76_最小覆盖子串 {

    public static void main(String[] args) {
        _76_最小覆盖子串 v = new _76_最小覆盖子串();
        System.out.println(v.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (s.equals(t)) return s;
        if (s.length() == 0 || s.length() < t.length()) return "";

        // t中字符对应的数量
        Map<Character, Integer> map = new HashMap<>();
        // 保存t中不同字符的数量。
        int count = 0;

        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                count++;
                map.put(t.charAt(i), 1);
            } else {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            }
        }

        int l = 0, r = 0;
        int temp = 0;
        String result = "";
        int min = Integer.MAX_VALUE;

        while (r < s.length()) {
            if (map.containsKey(s.charAt(r))) {
                Integer integer = map.get(s.charAt(r));
                if (integer == 1) {
                    temp++;
                }
                map.put(s.charAt(r), integer - 1);
            }
            // 寻找刚好覆盖t的左端点
            while (l < s.length() && temp == count) {
                if (map.containsKey(s.charAt(l))) {
                    Integer integer = map.get(s.charAt(l));
                    if (integer == 0) {
                        if ((r - l) < min) {
                            result = s.substring(l, r + 1);
                            System.out.println(result);
                            min = result.length();
                        }
                        temp--;
                    }
                    map.put(s.charAt(l), integer + 1);
                }
                l++;
            }
            r++;
        }

        return result;
    }

    public String minWindow1(String s, String t) {
        if (s.equals(t)) return s;
        if (s.length() == 0) return "";

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), 0);
            }
        }

        int l = 0, r = 0;
        int count = map.size();
        int temp = 0;
        Set<int[]> set = new HashSet<>();

        while (r < s.length()) {
            if (map.containsKey(s.charAt(r))) {
                Integer integer = map.get(s.charAt(r));
                if (integer == 0) {
                    temp++;
                }
                map.put(s.charAt(r), integer + 1);
            }
            while (l < s.length() && temp == count) {
                if (map.containsKey(s.charAt(l))) {
                    Integer integer = map.get(s.charAt(l));
                    if (integer == 1) {
                        set.add(new int[]{l, r});
                        temp--;
                    }
                    map.put(s.charAt(l), integer - 1);
                    l++;
                } else {
                    l++;
                }
            }
            r++;
        }

        String result = "";
        int n = Integer.MAX_VALUE;
        for (int[] ints : set) {
            String substring = s.substring(ints[0], ints[1] + 1);
            if (substring.length() < n) {
                result = substring;
                n = result.length();
            }
        }

        return result;
    }
}
