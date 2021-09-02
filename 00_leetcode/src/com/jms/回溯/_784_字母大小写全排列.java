package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/27 23:10
 * https://leetcode-cn.com/problems/letter-case-permutation/
 */
public class _784_字母大小写全排列 {
    public static void main(String[] args) {
        List<String> list = letterCasePermutation("a1b2");
        System.out.println(list);

//        System.out.println(1 << 5);
    }

    public static List<String> letterCasePermutation(String s) {
        if (s == null || s.length() == 0) return null;
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();

        dfs(0, chars, result);

        return result;
    }

    private static void dfs(int begin, char[] chars, List<String> result) {
        result.add(new String(chars));

        for (int i = begin; i < chars.length; i++) {
            if (Character.isLetter(chars[i])){
                char temp = chars[i];
                chars[i] = (char) (chars[i] - 'a' >= 0 ? chars[i] - 32 : chars[i] + 32);
                dfs(i + 1, chars, result);
                chars[i] = temp;
            }
        }
    }

    public static List<String> letterCasePermutation2(String s) {
        if (s == null || s.length() == 0) return null;
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();

        int B = 0;
        for (char aChar : chars) {
            if (Character.isLetter(aChar)){
                B++;
            }
        }

        for (int i = 0; i < 1 << B; i++) {
            int b = 0;
            StringBuilder stringBuilder = new StringBuilder();

            for (char aChar : chars) {
                if (Character.isLetter(aChar)){
                    if (((i >> b++) & 1) == 1){
                        stringBuilder.append(Character.toUpperCase(aChar));
                    }else {
                        stringBuilder.append(Character.toLowerCase(aChar));
                    }
                }else {
                    stringBuilder.append(aChar);
                }
            }

            result.add(stringBuilder.toString());
        }

        return result;
    }

    public static List<String> letterCasePermutation1(String s) {
        if (s == null || s.length() == 0) return null;
        List<StringBuilder> result = new ArrayList<>();
        result.add(new StringBuilder());
        char[] chars = s.toCharArray();


        for (char aChar : chars) {

            if (Character.isLetter(aChar)) {
                int size = result.size();
                for (int i = 0; i < size; i++) {
                    StringBuilder stringBuilder1 = new StringBuilder(result.get(i));
                    stringBuilder1.append(Character.toLowerCase(aChar));
                    result.add(stringBuilder1);

                    result.get(i).append(Character.toUpperCase(aChar));
                }
            }else {
                for (StringBuilder stringBuilder : result) {
                    stringBuilder.append(aChar);
                }
            }
        }

        List<String> collect = result.stream().map(stringBuilder -> stringBuilder.toString()).collect(Collectors.toList());
        return collect;
    }

}
