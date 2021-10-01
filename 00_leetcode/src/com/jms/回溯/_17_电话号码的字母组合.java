package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/13 20:12
 */
public class _17_电话号码的字母组合 {
    public static void main(String[] args) {
        List<String> list = letterCombinations("8");

        for (String s : list) {
            System.out.print(s + "  ");
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        df(0, digits, result, new StringBuilder());

        return result;
    }

    private static void df(int i, String digits, List<String> result, StringBuilder stringBuilder) {
        if (i == digits.length()) {
            result.add(stringBuilder.toString());
            return;
        }
        int value = Integer.parseInt(String.valueOf(digits.charAt(i)));

        int count = 3;
        if (value == 7 || value == 9) {
            count = 4;
        }

        for (int j = 0; j < count; j++) {
            if (value > 7) {
                stringBuilder.append((char) (value * 3 + 92 + j));
            } else {
                stringBuilder.append((char) (value * 3 + 91 + j));
            }
            df(i + 1, digits, result, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
