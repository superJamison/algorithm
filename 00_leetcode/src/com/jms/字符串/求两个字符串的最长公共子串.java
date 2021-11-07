package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/8 22:37
 */
public class 求两个字符串的最长公共子串 {

    public static void main(String[] args) {
        求两个字符串的最长公共子串 v = new 求两个字符串的最长公共子串();
        String s = v.lengthOfLongestSubstring("adbccadebbca", "edabccadebce");
        System.out.println(s);
    }

    public String lengthOfLongestSubstring(String s1, String s2) {
        int[][] record = new int[s1.length()][s2.length()];
        int maxLen = 0, maxEndIndex = 0;
        //  3 4 5   12

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)){
                    if (i == 0 || j == 0){
                        record[i][j] = 1;
                    }else {
                        record[i][j] = record[i - 1][j - 1] + 1;
                    }
                }

                if (record[i][j] > maxLen){
                    maxLen = record[i][j];
                    maxEndIndex = i;
                }
            }
        }

        return s1.substring(maxEndIndex - maxLen + 1, maxEndIndex + 1);
    }
}
