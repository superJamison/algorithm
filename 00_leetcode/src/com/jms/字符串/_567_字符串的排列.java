package com.jms.字符串;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/22 21:52
 * https://leetcode-cn.com/problems/permutation-in-string/
 */
public class _567_字符串的排列 {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }

    /**
     * 滑动窗口优化
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (s1 == null || n == 0) return false;
        if (s2 == null || m == 0) return false;
        if (n > m) return false;

        int[] cnt = new int[26];

        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']--;
            cnt[s2.charAt(i) - 'a']++;
        }
        int diff = 0;

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] != 0){
                diff++;
            }
        }

        if (diff == 0){
            return true;
        }

        for (int i = n; i < m; i++) {
            int x = s2.charAt(i - n) - 'a', y = s2.charAt(i) - 'a';
            if (x == y) continue;
            if (cnt[x] == 0){
                diff++;
            }
            cnt[x]--    ;
            if (cnt[x] == 0){
                diff--;
            }

            if (cnt[y] == 0){
                diff++;
            }
            cnt[y]++;
            if (cnt[y] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 滑动窗口
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion1(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return false;
        if (s2 == null || s2.length() == 0) return false;
        if (s1.length() > s2.length()) return false;

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(cnt1, cnt2)) return true;

        for (int i = s1.length(); i < s2.length(); i++) {
            cnt2[s2.charAt(i)  - 'a']++;
            cnt2[s2.charAt(i - s1.length()) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) return true;
        }

        return false;
    }
}
