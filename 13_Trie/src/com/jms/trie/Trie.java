package com.jms.trie;

import java.util.HashMap;

/**
 * 单词树 / 前缀树
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/1 20:57
 */
public class Trie<V> {

    private int size;
    private Node<V> root = new Node<>();

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
    }

    public V get(String key){
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    public boolean contains(String key){
        return node(key) != null;
    }

    public V add(String key, V value){

        return null;
    }

    public V remove(String key){
        return null;
    }

    public boolean startsWith(String prefix){
        return false;
    }

    private Node<V> node(String key){
        if (root == null) return null;
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if (node == null) return null;
        }
        return node.word ? node : null;
    }

    private void keyCheck(String key){
        if (key == null || key.length() == 0){
            throw new IllegalArgumentException("Key must be not null");
        }
    }

    public static class Node<V>{
        HashMap<Character, Node<V>> children;
        public V value;
        public boolean word; //是否为单词的结尾（判断是否为一个单词）

        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }
    }

}
