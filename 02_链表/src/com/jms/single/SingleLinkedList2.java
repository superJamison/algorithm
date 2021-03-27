package com.jms.single;

import java.util.AbstractList;
import java.util.List;

/**
 * 增加一个虚拟头结点
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/3/18 22:48
 */
public class SingleLinkedList2<T> extends AbstractList<T> {
    private int size;
    private Node<T> first;

    private static final int ELEMENT_NOT_FOUND = -1;

    public SingleLinkedList2() {
        first = new Node<>(null, null);
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    /**
     * 在指定index位置设置元素
     *
     * @param index   索引
     * @param element 需要设置到链表中的元素
     * @return 返回原先的元素
     */
    @Override
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        T oldEle = node.element;
        node.element = element;
        return oldEle;
    }

    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }

    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        Node<T> preNode = index == 0 ? first : getNode(index - 1);
        preNode.next = new Node<>(element, preNode.next);
        size++;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Node<T> pre = index == 0 ? first : getNode(index - 1);
        Node<T> node = pre.next;
        pre.next = node.next;
        size--;
        return node.element;
    }

    @Override
    public int indexOf(Object element) {
        Node<T> node = first.next;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            element = (T) element;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 获取指定index位置的节点元素
     *
     * @param index
     * @return
     */
    private Node<T> getNode(int index) {
        rangeCheck(index);
        Node<T> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("位置超出了范围！Index:" + index + ", Size:" + size);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("您要添加的位置超出了范围！Index:" + index + ", Size:" + size);
        }
    }

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        //size=3, [12, 13, 14]
        StringBuilder builder = new StringBuilder();
        builder.append("ArrayList{size=").append(size).append(", [");
        if (first != null){
            Node<T> node = first.next;
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    builder.append(node.element);
                } else {
                    builder.append(", ").append(node.element);
                }
                node = node.next;
            }
        }
        builder.append("]}");
        return builder.toString();
    }

}
