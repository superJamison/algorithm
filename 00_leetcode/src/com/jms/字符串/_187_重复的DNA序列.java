package com.jms.字符串;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/8 11:09
 */
public class _187_重复的DNA序列 {

    public static void main(String[] args) {
        _187_重复的DNA序列 v = new _187_重复的DNA序列();

        System.out.println(v.findRepeatedDnaSequences("AAAAAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < s.length() - 9; i++) {
            String substring = s.substring(i, i + 10);

            if (set.contains(substring) && !list.contains(substring)){
                list.add(substring);
            }else {
                set.add(substring);
            }
        }

        return list;
    }
}
