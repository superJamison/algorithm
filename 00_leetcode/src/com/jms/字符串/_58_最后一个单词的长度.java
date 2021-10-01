package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/21 16:42
 */
public class _58_最后一个单词的长度 {
    public static void main(String[] args) {
        int i = lengthOfLastWord("Hello World");

        System.out.println(i);
    }

    public static int lengthOfLastWord(String s) {
        s = s.trim();

        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' '){
                break;
            }
            count++;
        }

        return count;
    }

    public static int lengthOfLastWord1(String s) {
        String trim = s.trim();
        String[] s1 = trim.split(" ");
        return s1[s1.length - 1].length();
    }
}
