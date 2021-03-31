package com.jms.map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/30 22:37
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {
        LinkedNode<K, V>  node1 = (LinkedNode<K, V>) willNode;
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) removeNode;

        if (node1 != node1){//willNode是度为二的节点
            //交换willNode和removeNode在链表中的位置

            //交换prev
            LinkedNode<K, V> tmp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = tmp;
            if (node1.prev == null){
                first = node1;
            }else {
                node1.prev.next = node1;
            }
            if (node2.prev == null) {
                first = node2;
            }else {
                node2.prev.next = node2;
            }

            //交换next
            tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;
            if (node1.next == null){
                last = node1;
            }else {
                node1.next.prev = node1;
            }
            if (node2.next == null) {
                last = node2;
            }else {
                node2.next.prev = node2;
            }
        }

        if (removeNode == null){
            return;
        }

        LinkedNode<K, V> prev = node2.prev;
        LinkedNode<K, V> next = node2.next;
        if (prev == null){
            first = next;
        }else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        }else {
            next.prev = prev;
        }
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)){
                return;
            }
            node = node.next;
        }
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parentNode) {
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parentNode);
        if (first == null){
            first = last = node;
        }else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    protected static class LinkedNode<K, V> extends Node<K, V>{
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;

        public LinkedNode(K key, V value, Node parent) {
            super(key, value, parent);
        }

    }

}
