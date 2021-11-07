package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/7 13:13
 */
public class _434_字符串中的单词数 {

    public int countSegments(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' '){
                result++;
            }
        }

        return result;
    }

    public int countSegments1(String s) {
        if (s == null || s.length() == 0) return 0;

        s = s.trim();
        if ("".equals(s)) return 0;

        return s.split("\\s+").length;
    }
}
