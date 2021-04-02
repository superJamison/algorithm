package com.jms;

import com.jms.trie.Trie;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/1 20:56
 */
public class Main {
    public static void main(String[] args) {
        testTrie();
    }

    private static void testTrie() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("Jamison", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.startsWith("c"));
        Asserts.test(trie.startsWith("ca"));
        Asserts.test(trie.startsWith("cat"));
        Asserts.test(trie.startsWith("cata"));
        Asserts.test(trie.get("Jamison") == 5);
        Asserts.test(trie.remove("Jamison") == 1);
        Asserts.test(trie.remove("catalog") == 3);
        Asserts.test(trie.remove("cast") == 4);
        Asserts.test(trie.size() == 2);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(trie.startsWith("c"));
    }
}
