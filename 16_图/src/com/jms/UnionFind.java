package com.jms;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/10 19:53
 */
public class UnionFind<T> {
    private Map<T, Node<T>> nodes = new HashMap<>();

    public void makeSet(T t){
        if (nodes.containsKey(t)) return;
        nodes.put(t, new Node<>(t));
    }

    public T find(T t){
        Node<T> node = findNode(t);
        return node == null ? null : node.value;
    }

    /**
     * 查找t的根节点node
     */
    private Node<T> findNode(T t) {
        Node<T> node = nodes.get(t);
        if (node == null) return null;
        Node<T> p = null;
        while (!Objects.equals(node.value, node.parent.value)){
            p = node.parent;
            node.parent = node.parent.parent;
            node = p;
        }
        return node;
    }

    public void union(T t1, T t2){
        Node<T> p1 = findNode(t1);
        Node<T> p2 = findNode(t2);
        if (p1 == null || p2 == null)return;
        if (Objects.equals(p1.value, p2.value))return;

        if (p1.rank < p2.rank){
            p1.parent = p2;
        }else if (p2.rank < p1.rank){
            p2.parent = p1;
        }else {
            p1.parent = p2;
            p2.rank += 1;
        }
    }

    public boolean isSame(T t1, T t2){
        return Objects.equals(find(t1), find(t2));
    }

    private static class Node<T>{
        T value;
        Node<T> parent = this;
        int rank = 1;

        public Node(T value) {
            this.value = value;
        }
    }

}
