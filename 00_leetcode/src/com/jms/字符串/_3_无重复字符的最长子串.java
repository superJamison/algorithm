package com.jms.字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/30 23:09
 */
public class _3_无重复字符的最长子串 {
    public static void main(String[] args) {
        System.out.println("".length());
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] prevIndex = new int[128];
        for (int i = 0; i < prevIndex.length; i++) {
            prevIndex[i] = -1;
        }
        prevIndex[chars[0]] = 0;
        int max = 1;
        // 以i-1位置结尾的无重复字符的最长子串的开始字符
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            // i位置上的字符上一次出现的位置
            int pi = prevIndex[chars[i]];
            if (pi >= li){
                li = pi + 1;
            }
            max = Math.max(max, i - li + 1);
            prevIndex[chars[i]] = i;
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null && s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put(chars[0], 0);
        int max = 1;
        // 以i-1位置结尾的无重复字符的最长子串的开始字符
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            // i位置上的字符上一次出现的位置
            Integer pi = map.get(chars[i]);
            if (pi != null && pi >= li){
                li = pi + 1;
            }
            max = Math.max(max, i - li + 1);
            map.put(chars[i], i);
        }
        return max;
    }
}
