package com.jms.字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/19 16:15
 */
public class _211_添加与搜索单词_数据结构设计 {

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();

        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        System.out.println(dictionary.search("pad"));
        System.out.println(dictionary.search("bad"));
        System.out.println(dictionary.search(".ad"));
        System.out.println(dictionary.search("b.."));

//        dictionary.addWord("a");
//        System.out.println(dictionary.search("a"));
    }

}

class WordDictionary {

    private Set<String> set;
    private Map<String, Boolean> hasContain;
    private Boolean hashModify;

    public WordDictionary() {
        hashModify = true;
        set = new HashSet<>();
        hasContain = new HashMap<>();
    }

    public void addWord(String word) {
        hashModify = true;
        set.add(word);
    }

    public boolean search(String word) {
        if (!hashModify && hasContain.containsKey(word)){
            return hasContain.get(word);
        }
        hashModify = false;
        for (String s : set) {
            if (word.length() != s.length()) continue;

            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == s.charAt(i) || word.charAt(i) == '.') continue;
                flag = false;
            }
            if (flag) {
                hasContain.put(word, true);
                return true;
            }
        }
        hasContain.put(word, false);
        return false;
    }
}

class WordDictionary1 {

    private StringBuilder builder;

    public WordDictionary1() {
        builder = new StringBuilder();
    }

    public void addWord(String word) {
        builder.append(word);
    }

    public boolean search(String word) {
        int i2 = 0;
        while (i2 < word.length() && word.charAt(i2) == '.') {
            i2++;
        }
        if (i2 == word.length()) return true;
        int oldI2 = i2;
        int i1 = i2;

        while (i1 < builder.length()) {
            if (i2 == word.length()) return true;
            if (word.charAt(i2) == '.' || builder.charAt(i1) == word.charAt(i2)) {
                i1++;
                i2++;
            } else {
                i1++;
                i2 = oldI2;
            }
        }

        return i2 == word.length();
    }
}

