package com.jms;

import com.jms.file.FileInfo;
import com.jms.file.Files;
import com.jms.set.ListSet;
import com.jms.set.Set;
import com.jms.set.TreeSet;
import com.jms.util.Times;
import sun.reflect.generics.tree.Tree;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 16:30
 */
public class Main {

    static void test(){
        Set<Integer> set = new ListSet<>();
        set.add(1);
        set.add(44);
        set.add(133);
        set.add(33);
        set.add(44);
        set.add(22);
        set.add(1);
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    static void test1(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(44);
        set.add(133);
        set.add(33);
        set.add(44);
        set.add(22);
        set.add(1);
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    static String[] getWord(){
        FileInfo read = Files.read("D:\\study\\java\\资料\\算法\\jdk-src\\java\\util\\concurrent\\atomic", new String[]{"java"});
//        System.out.println("文件数量：" + read.getFiles());
//        System.out.println("代码行数：" + read.getLines());
//        System.out.println("单词数量：" + read.words().length);
        return read.words();
    }



    private static void test3() {
        Set<String> listSet = new ListSet<>();
        Times.test("ListSet:", new Times.Task() {
            @Override
            public void execute() {
                optionSet(listSet, getWord());
            }
        });

        Set<String> treeSet = new TreeSet<>();
        Times.test("TreeSet:", new Times.Task() {
            @Override
            public void execute() {
                optionSet(treeSet, getWord());
            }
        });
    }

    private static void optionSet(Set<String> set, String[] word) {
        for (int i = 0; i < word.length; i++) {
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

    public static void main(String[] args) {
        test3();
    }


}
