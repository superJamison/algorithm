package com.jms.字符串;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/2 12:20
 */
public class _剑指_Offer_50_第一个只出现一次的字符 {

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';

        int[] ints = new int[26];

        for (int i = 0; i < s.length(); i++) {
            ints[s.charAt(i) - 'a'] = ints[s.charAt(i) - 'a'] + 1;
        }

        for (int i = 0; i < s.length(); i++) {
            if (ints[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }

        return ' ';
    }

    public char firstUniqChar2(String s) {
        if (s == null || s.length() == 0) return ' ';

        Map<Character, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (Character character : map.keySet()) {
            if (map.get(character) == 1){
                return character;
            }
        }

        return ' ';
    }

    public char firstUniqChar1(String s) {
        if (s == null || s.length() == 0) return ' ';

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }

        return ' ';
    }

}
