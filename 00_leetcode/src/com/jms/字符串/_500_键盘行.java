package com.jms.字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/31 11:51
 */
public class _500_键盘行 {

    public static void main(String[] args) {
        _500_键盘行 v = new _500_键盘行();
        String[] words = v.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"});

        for (String word : words) {
            System.out.println(word);
        }
    }

    private final char[][] chars = {
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'},
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'},
            {'z', 'x', 'c', 'v', 'b', 'n', 'm','Z', 'X', 'C', 'V', 'B', 'N', 'M'}
    };

    public String[] findWords(String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                map.put(chars[i][j], i);
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            int cur = -1;
            boolean flag = true;
            for (int j = 0; j < chars.length; j++) {
                Integer row = map.get(chars[j]);
                if (cur != -1 && row != cur) {
                    flag = false;
                    break;
                }
                cur = row;
            }
            if (flag){
                list.add(words[i]);
            }
        }

        return list.toArray(new String[list.size()]);
    }
}
