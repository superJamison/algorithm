package com.jms;

import com.jms.entity.Key;
import com.jms.entity.Person;
import com.jms.map.HashMap;
import com.jms.map.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/29 17:04
 */
public class Main {

    private static void testHash1(){
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
        System.out.println(map.size());
        map.traversal(new Map.Visitor<Key, Integer>() {
            @Override
            public boolean visit(Key key, Integer value) {
                System.out.println(key + "_" +value);
                return false;
            }
        });
        System.out.println(map.get(new Key(1)));
    }
    public static void main(String[] args) {
        test4();
    }
}
