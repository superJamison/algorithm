package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/30 22:01
 */
public class _151_翻转字符串里的单词 {
    public static void main(String[] args) {
        System.out.println("_" + reverseWords("") + "_");
        System.out.println("_" + reverseWords("      ") + "_");
        System.out.println(reverseWords("   hello    world   "));
        System.out.println(reverseWords("are you   ok"));
        System.out.println(reverseWords("My name is Jamison "));
    }

    public static String reverseWords(String s) {
        if (s == null && s.length() == 0) return "";
        char[] chars = s.toCharArray();
        // 字符串的有效长度
        int len = 0;
        int cur = 0;
        // i位置的前一个是否为第一个空格字符
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' '){ // 非空格字符
                chars[cur] = chars[i];
                cur++;
                flag = false;
            }else { // 空格字符
                if (!flag){
                    // 前一个不是第一个空格
                    chars[cur] = ' ';
                    cur++;
                }
                flag = true;
            }
        }
        // 如果最后一个是空格字符，则len=cur-1，否则len=cur
        len = flag ? cur - 1 : cur;
        if (len <= 0) return "";
        // 对整个有效字符串进行逆序
        reverse(chars, 0, len);

        // 对有序字符里面的每个单词进行逆序
        // 前一个空格字符的位置
        int preSpaceIndex = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' '){
                reverse(chars, preSpaceIndex + 1, i);
                preSpaceIndex = i;
            }
        }
        // 翻转最后一个单词
        reverse(chars, preSpaceIndex + 1, len);

        return new String(chars, 0, len);
    }

    /**
     * 对[li, ri)范围内的chars的字符进行逆转
     */
    private static void reverse(char[] chars, int li, int ri) {
        ri--;
        while (li < ri){
            char tmp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = tmp;
            li++;
            ri--;
        }
    }
}
