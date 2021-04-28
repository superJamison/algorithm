package com.jms;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/26 22:01
 */
public class SkipList<K, V> {
    private int size;
    private Comparator<K> comparator;
    private static final int MAX_LEVEL = 32;
    private static final double p = .25;
    private Node<K, V> first;
    private int level = 1;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator){
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V put(K key, V value){
        keyCheck(key);
        // 赋值第一个节点
        Node<K, V> node = first;
        int cmp = -1;
        // 保存前驱节点
        Node<K, V>[] prev = new Node[level];
        // 从level-1层开始查找，直到key大于了这个节点的key值，就往下一层查找level--
        for (int i = level - 1; i >= 0; i--) {
            while (node.nexts[i] != null && (cmp = cmp(key, node.nexts[i].key)) > 0){
                node = node.nexts[i];
            }
            // 当比较值为0时，说明新添加的节点是已经存在的,就将原先的value换掉，并返回原先的value值
            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            prev[i] = node;
        }
        // 随机生成level
        int newLevel = randomLevel();
        // 新建一个节点
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        for (int i = 0; i < newLevel; i++) {
            if (i >= level){
                first.nexts[i] = newNode;
            }else {
               newNode.nexts[i] = prev[i].nexts[i];
               prev[i].nexts[i] = newNode;
            }
        }
        // 更新level
        level = Math.max(newLevel, level);
        // 节点数量增加
        size++;
        return null;
    }

    /**
     * 随机生成一个level层数，模仿redis官方源码的做法
     */
    private int randomLevel() {
        int level = 1;
        while (Math.random() < p && level < MAX_LEVEL){
            level++;
        }
        return level;
    }

    public V get(K key){
        // 检查key值是否为空
        keyCheck(key);
        // 赋值第一个节点
        Node<K, V> node = first;
        int cmp = -1;
        // 从level-1层开始查找，直到key大于了这个节点的key值，就往下一层查找level--
        for (int i = level - 1; i >= 0; i--) {
            while (node.nexts[i] != null && (cmp = cmp(key, node.nexts[i].key)) > 0){
                node = node.nexts[i];
            }
            // 当比较值为0时，说明查找到了这个key对应的value，则返回
            if (cmp == 0) return node.nexts[i].value;
        }
        // 如果走到这里说明链表中没有对应的key和value
        return null;
    }

    public V remove(K key){
        keyCheck(key);
        // 赋值第一个节点
        Node<K, V> node = first;
        int cmp = -1;
        // 保存前去节点
        Node<K, V>[] prev = new Node[level];
        // 判断是否存在这个节点
        boolean exits = false;
        // 从level-1层开始查找，直到key大于了这个节点的key值，就往下一层查找level--
        for (int i = level - 1; i >= 0; i--) {
            while (node.nexts[i] != null && (cmp = cmp(key, node.nexts[i].key)) > 0){
                node = node.nexts[i];
            }
            // 当比较值为0时，说明新添加的节点是已经存在的,就将原先的value换掉，并返回原先的value值
            if (cmp == 0) exits = true;
            prev[i] = node;
        }
        // 节点不存在，直接返回
        if (!exits) return null;
        // 数量减1
        size--;
        // 说明节点存在，则删除节点，并更新他的前驱节点的next
        Node<K, V> removeNode = node.nexts[0];
        // 设置后继
        for (int i = 0; i < removeNode.nexts.length; i++) {
            prev[i].nexts[i] = removeNode.nexts[i];
        }
        // 如果删除的节点的level是最高的话，就需要更新level
        int newLevel = level;
        for (int i = newLevel - 1; i >= 0; i--) {
            if (first.nexts[i] != null){
                break;
            }
            newLevel = i;
        }
        level = newLevel;

        return removeNode.value;
    }

    private int cmp(K k1, K k2){
        if (this.comparator != null){
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>)k1).compareTo(k2);
    }

    private void keyCheck(K key) {
        if (key == null){
            throw new IllegalArgumentException("key must not be null.");
        }
    }

    private static class Node<K, V>{
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }

        @Override
        public String toString() {
            return key + "_" + value + "_" + nexts.length;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append("  ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
