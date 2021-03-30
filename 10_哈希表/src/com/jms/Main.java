package com.jms;

import com.jms.entity.Key;
import com.jms.entity.SubKey1;
import com.jms.entity.SubKey2;
import com.jms.file.FileInfo;
import com.jms.file.Files;
import com.jms.map.HashMap;
import com.jms.map.LinkedHashMap;
import com.jms.map.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/29 17:04
 */
public class Main {

    /*private static void testHash1(){
        Person p1 = new Person(10, 1.71f, "Jamison");
        Person p2 = new Person(10, 1.71f, "Jamison");
        Map<Object, Object> map = new HashMap<>();
        map.put(p1, "abc");
        map.put("test", "1234");
        map.put(p2, "bcd");
        System.out.println(map.size());
    }

    private static void test2(){
        Person p1 = new Person(10, 1.71f, "Jamison");
        Person p2 = new Person(10, 1.71f, "Jamison");
        Map<Object, Object> map = new HashMap<>();
        map.put(p1, 1);
        map.put(p2, 2);
        map.put("Jamison", 3);
        map.put("Rose",4);
        map.put(p2, 5);
        map.put(null, 6);
        map.put(null, 7);
//        System.out.println(map.size());

//        System.out.println(map.get("Jamison"));
//        map.remove("Jamison");
//        System.out.println(map.get("Jamison"));
//        map.remove(p1);
//        System.out.println(map.get(p1));
//        System.out.println(map.get("Rose"));
//        map.remove(null);
//        System.out.println(map.get(null));
//        map.traversal(new Map.Visitor<Object, Object>() {
//            @Override
//            public boolean visit(Object key, Object value) {
//                System.out.println(key + "_" + value);
//                return false;
//            }
//        });
        System.out.println(map.containKey(p1));
        System.out.println(map.containKey(null));
        System.out.println(map.containKey("Jamison"));
        System.out.println(map.containValue(1));
        System.out.println(map.containValue(4));
    }

    private static void test4(){
        HashMap<Key, Integer> map = new HashMap<>();
        for (int i = 1; i <= 19; i++) {
            map.put(new Key(i), i);
        }
        map.put(new Key(4), 100);
        System.out.println(map.size());
        map.traversal(new Map.Visitor<Key, Integer>() {
            @Override
            public boolean visit(Key key, Integer value) {
//                System.out.println(key + "_" +value);
                return false;
            }
        });
        System.out.println(map.get(new Key(4)));
    }*/

    static void test1Map(Map<String, Integer> map, String[] words) {
        Times.test(map.getClass().getName(), new Times.Task() {
            @Override
            public void execute() {
                for (String word : words) {
                    Integer count = map.get(word);
                    count = count == null ? 0 : count;
                    map.put(word, count + 1);
                }
                System.out.println(map.size()); // 17188

                int count = 0;
                for (String word : words) {
                    Integer i = map.get(word);
                    count += i == null ? 0 : i;
                    map.remove(word);
                }
                Asserts.test(count == words.length);
                Asserts.test(map.size() == 0);
            }
        });
    }

    static void test1() {
        String filepath = "D:\\study\\java\\资料\\算法\\jdk-src\\java\\util";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

//        test1Map(new TreeMap<>(), words);
        test1Map(new HashMap<>(), words);
//        test1Map(new LinkedHashMap<>(), words);
        /**
         * 总行数：62399
         * 单词总数：256149
         * -------------------------------------
         * 【java.util.TreeMap】
         * 开始：17:37:40.190
         * 7705
         * 结束：17:37:40.343
         * 耗时：0.152秒
         * -------------------------------------
         * 【java.util.LinkedHashMap】
         * 开始：17:37:40.345
         * 7705
         * 结束：17:37:40.401
         * 耗时：0.056秒
         * -------------------------------------
         * 【com.jms.map.HashMap】
         * 开始：17:43:35.777
         * 7705
         * 结束：17:43:35.866
         * 耗时：0.089秒
         * -------------------------------------
         */
    }

    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == 10);
        Asserts.test(map.get(new Key(6)) == 11);
        Asserts.test(map.get(new Key(7)) == 12);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Asserts.test(map.size() == 5);
        Asserts.test(map.get(null) == 8);
        Asserts.test(map.get("jack") == 6);
        Asserts.test(map.get(10) == null);
        Asserts.test(map.get(new Object()) == null);
        Asserts.test(map.containKey(10));
        Asserts.test(map.containKey(null));
        Asserts.test(map.containValue(null));
        Asserts.test(map.containValue(1) == false);
    }

    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
//        map.remove("jack");
//        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
//        for (int i = 5; i <= 7; i++) {
//            Asserts.test(map.remove(new Key(i)) == i);
//        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
//        Asserts.test(map.size() == 19);
//        Asserts.test(map.get(new Key(1)) == 6);
//        Asserts.test(map.get(new Key(2)) == 7);
//        Asserts.test(map.get(new Key(3)) == 8);
//        Asserts.test(map.get(new Key(4)) == 4);
//        Asserts.test(map.get(new Key(5)) == null);
//        Asserts.test(map.get(new Key(6)) == null);
//        Asserts.test(map.get(new Key(7)) == null);
//        Asserts.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
//                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }

    public static void main(String[] args) {
//        test4();

//        Map<Key, Object> map = new HashMap<>();
//        SubKey1 subKey1 = new SubKey1(1);
//        SubKey2 subKey2 = new SubKey2(1);
//        map.put(subKey1, 1);
//        map.put(subKey2, 2);
//        System.out.println(map.size());
//        test1();
//        test2(new HashMap<>());
//        test3(new HashMap<>());
//        test4(new HashMap<>());
        test4(new LinkedHashMap<>());
//        test5(new HashMap<>());
    }
}
