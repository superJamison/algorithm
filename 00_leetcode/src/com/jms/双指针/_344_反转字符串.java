package com.jms.双指针;

import java.nio.channels.Channel;
import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/19 20:53
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class _344_反转字符串 {

    public static void main(String[] args) {
        reverseString(new char[]{'h','e','l','l','o'});
    }

    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;

        while (left < right){
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }

        System.out.println(new String(s));
    }
}
