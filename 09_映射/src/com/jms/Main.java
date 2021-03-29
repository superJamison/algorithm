package com.jms;

import com.jms.file.FileInfo;
import com.jms.file.Files;
import com.jms.map.Map;
import com.jms.map.TreeMap;
import com.jms.printer.BinaryTrees;
import com.jms.set.Set;
import com.jms.set.TreeSet;
import com.jms.util.Times;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 21:12
 */
public class Main {

    static void test(){
        Map<String, Integer> map = new TreeMap<>();
        map.put("", 12);
        map.put("a",44);
        map.put("b", 33);
        map.put("c", 12);
        map.put("f", 66);
        map.put("g", 66);
        map.put("v", 66);
        map.put("r", 66);
        map.put("e", 66);


        map.remove("c");
        map.remove("a");
        map.remove("b");
        map.remove("");
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    private static void test1(){
        FileInfo read = Files.read("D:\\study\\java\\code\\算法\\08_集合\\src\\com\\jms\\tree", new String[]{"java"});
        System.out.println("文件数量：" + read.getFiles());
        System.out.println("代码行数：" + read.getLines());
        String[] words = read.words();
        System.out.println("单词数量：" + words.length);

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = count == null ? 1 : count + 1;
            map.put(words[i], count);
        }
        System.out.println("---------------------");
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key);
                return false;
            }
        });

        for (int i = 0; i < words.length; i++) {
            System.out.println("--------words-------------");
            System.out.println(words[i]);
            map.remove(words[i]);
        }

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                return false;
            }
        });

    }

    static String[] getWord(){
        FileInfo read = Files.read("D:\\study\\java\\资料\\算法\\jdk-src\\java\\util\\concurrent\\atomic", new String[]{"java"});
        return read.words();
    }

    private static void test3() {
        Set<String> listSet = new TreeSet<>();
        Times.test("TreeSet:", new Times.Task() {
            @Override
            public void execute() {
                optionSet(listSet, getWord());
            }
        });

    }

    private static void optionSet(Set<String> set, String[] word) {
        for (int i = 0; i < word.length; i++) {
            System.out.println(word[i] + "\n--------------------------");
            set.add(word[i]);
        }
        System.out.println("去重之后单词数量：" + set.size());
        for (int i = 0; i < word.length; i++) {
            set.contains(word[i]);
        }
        for (int i = 0; i < word.length; i++) {
            set.remove(word[i]);
        }

    }

    private static void test4() {
        FileInfo read = Files.read("D:\\study\\java\\code\\算法\\08_集合\\src\\com\\jms", new String[]{"java"});
        String[] words = read.words();

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = count == null ? 1 : count + 1;
            map.put(words[i], count);
        }
        BinaryTrees.print(map);
    }

    public static void main(String[] args) {
        test1();
    }
}
