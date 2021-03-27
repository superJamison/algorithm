package com.jms;



import java.util.AbstractList;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/18 22:48
 */
public class LinkedList<T> extends AbstractList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    private static final int ELEMENT_NOT_FOUND = -1;

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
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
        if (index == size) {//往最后面添加一个元素
            Node<T> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) {//这是链表添加的第一个元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<T> next = getNode(index);
            Node<T> prev = next.prev;
            Node<T> node = new Node<>(prev, element, next);
            next.prev = node;
            if (prev == null) {// index = 0
                first = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Node<T> node = getNode(index);
        Node<T> next = node.next;
        Node<T> prev = node.prev;
        if (next == null) { //index == size - 1
            last = prev;
        } else {
            next.prev = prev;
        }
        if (prev == null) { //index = 0
            first = next;
        } else {
            prev.next = next;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(Object element) {
        Node<T> node = first;
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
        if (index < (size >> 1)) {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<T> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

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
        Node<T> prev;

        public Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (prev != null){
                builder.append(prev.element);
            }else {
                builder.append("null");
            }
            builder.append("_" + element + "_");
            if (next != null){
                builder.append(next.element);
            }else {
                builder.append("null");
            }
            return builder.toString();
        }
    }

    @Override
    public String toString() {
        //size=3, [12, 13, 14]
        StringBuilder builder = new StringBuilder();
        builder.append("LinkedList{size=").append(size).append(", [");
        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                builder.append(node);
            } else {
                builder.append(", ").append(node);
            }
            node = node.next;
        }
        builder.append("]}");
        return builder.toString();
    }

}
