package com.jms.map;

import com.jms.printer.BinaryTreeInfo;
import com.jms.printer.BinaryTrees;

/**
 * Map 接口
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 21:12
 */
public interface Map<K, V> extends BinaryTreeInfo {

    int size();
    boolean isEmpty();
    void clear();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containKey(K key);
    boolean containValue(V value);
    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V>{
        boolean stop;
        public abstract boolean visit(K key, V value);
    }
}
