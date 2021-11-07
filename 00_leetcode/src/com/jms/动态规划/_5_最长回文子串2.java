package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/29 11:45
 */
public class _5_最长回文子串2 {

    int maxLen = 1, begin = 0;

    // 马拉车算法
    public String longestPalindrome(String s){
        return s;
    }

    // 扩展中心法
    public String longestPalindrome2(String s) {
        int n = s.length();
        if (n == 0) return s;
        char[] chars = s.toCharArray();

        for (int i = 0; i < n - 1; i++) {
            // 以每个字符为中心向两边扩展
            longestPalindromeExtend(chars, i, i);
            // 以i字符和i+1个字符为中心扩展
            longestPalindromeExtend(chars, i, i + 1);
        }

        return s.substring(begin, begin + maxLen);
    }

    private void longestPalindromeExtend(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length && chars[i] == chars[j]){
            i--;
            j++;
        }
        int curLen = j - i - 1;
        if (curLen > maxLen){
            maxLen = curLen;
            begin = i + 1;
        }
    }

    // 动态规划
    public String longestPalindrome1(String s) {
        int n = s.length();
        if (n == 1) return s;
        char[] chars = s.toCharArray();

        // dp[i][j]表示s[i, j]是否是回文子串
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1, begin = 0;
        // 从后往前遍历s
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                int curLen = j - i + 1;
                dp[i][j] = chars[i] == chars[j] && (curLen <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && curLen > maxLen){
                    maxLen = curLen;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }
}
