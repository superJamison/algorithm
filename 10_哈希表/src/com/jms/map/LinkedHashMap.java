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
    public V remove(Node<K, V> node) {
        if (node == null){
            return null;
        }
        LinkedNode<K, V> linkedNode = (LinkedNode<K, V>) node;
        LinkedNode<K, V> prev = linkedNode.prev;
        LinkedNode<K, V> next = linkedNode.next;
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
        return super.remove(node);
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
