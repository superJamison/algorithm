package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/13 21:07
 */
public class _22_括号生成 {
    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        for (String s : list) {
            System.out.print(s + "  ");
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;

        dfs(0, n, 0, 0, new StringBuilder(), result);

        return result;
    }

    /**
     * @param i 构造第i个括号
     * @param n 有效括号的个数
     * @param left 第left个括号
     * @param right 第right个括号
     */
    private static void dfs(int i, int n, int left, int right, StringBuilder stringBuilder, List<String> result) {
        // 终止条件
        if (i == n * 2) {
            result.add(stringBuilder.toString());
            return;
        }

        // 左括号还没有到n个，这时候可以添加左括号
        if (left < n){
            stringBuilder.append('(');
            dfs(i + 1, n, left + 1, right, stringBuilder, result);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        // 右括号比左括号少，添加右括号
        if (left > right){
            stringBuilder.append(')');
            dfs(i + 1, n, left, right + 1, stringBuilder, result);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
