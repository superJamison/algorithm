package com.jms.字符串;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/25 12:47
 */
public class _剑指_Offer_38_字符串的排列 {

    public static void main(String[] args) {
        _剑指_Offer_38_字符串的排列 v = new _剑指_Offer_38_字符串的排列();
        for (String abc : v.permutation("aba")) {
            System.out.println(abc);
        }
    }

    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation1(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }


    public String[] permutation(String s) {
        if (s == null || "".equals(s)) return new String[0];
        Set<String> set = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        builder.append(s.charAt(0));

        dfs(s, set, builder, 1);

        return set.toArray(new String[set.size()]);
    }

    private void dfs(String s, Set<String> set, StringBuilder builder, int i) {
        if (i == s.length()) {
            set.add(builder.toString());
            return;
        }

        char charAt = s.charAt(i);
        for (int j = 0; j <= builder.length(); j++) {
            builder.insert(j, charAt);
            dfs(s, set, builder, i + 1);
            builder.deleteCharAt(j);
        }
    }
}
