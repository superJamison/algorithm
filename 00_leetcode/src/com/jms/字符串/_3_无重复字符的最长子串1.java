package com.jms.字符串;

import java.util.HashMap;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/22 21:02
 */
public class _3_无重复字符的最长子串1 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] chars = s.toCharArray();
        // 保存chars[i] 位置的上一次出现的索引位置
        int[] prevIndex = new int[128];

        int max = 1, left = 0;

        for (int i = 0; i < prevIndex.length; i++) {
            prevIndex[i] = -1;
        }

        prevIndex[chars[0]] = 0;

        for (int i = 1; i < chars.length; i++) {
            int pi = prevIndex[chars[i]];

            if (pi >= left){
                left = pi + 1;
            }
            max = Math.max(max, i - left + 1);
            prevIndex[chars[i]] = i;
        }

        return max;
    }

}
