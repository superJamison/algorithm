package com.jms.tree;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 21:31
 */
public class BST<T> extends BinaryTree<T> {

    private Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BST() {
        this(null);
    }

    public void add(T element) {
        elementNotNullCheck(element);
        //添加第一个节点
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        //添加的不是第一个节点
        //找到父节点
        Node<T> node = root;
        Node<T> parentNode = root;
        int compare = 0;
        while (node != null) {
            compare = compare(element, node.element);
            parentNode = node;
            if (compare > 0) {
                //右边
                node = node.right;
            } else if (compare < 0) {
                //左边
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<T> newNode = createNode(element, parentNode);
        //看看插入的节点位置是在那个位置
        if (compare > 0) {
            parentNode.right = newNode;
        } else if (compare < 0) {
            parentNode.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * 添加node之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<T> node){}

    /**
     * 删除node之后的调整
     * @param node 删除的节点
     */
    protected void afterRemove(Node<T> node){}

    public void remove(T element) {
        remove(node(element));
    }

    private void remove(Node<T> node) {
        if (node == null) {
            return;
        }

        // 节点存在，必定要删除
        size--;
        /**
         * 删除度为2的节点：
         * 1.先用前驱或者后继节点的值覆盖原节点的值
         * 2.然后删除相应的前驱或者后继
         * 3 .如果一个节点的度为2，那么：它的前驱或者后继的度只可能是1或0
         */
        if (node.hasTwoLeaf()) {// 代表是度为2，存在左右子节点
            // 找到后继节点
            Node<T> successor = successor(node);
            // 用后继节点的值覆盖node节点的值
            node.element = successor.element;
            // 删除后继节点
            node = successor;
        }

        // 删除node的节点(node的度必然是1或0)
        Node<T> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {//node是度为1的节点
            //更改parent
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {//node是叶子节点并且是root节点
            root = null;
        } else {// node是叶子结点，但不是root节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        //删除节点之后的处理
        afterRemove(node);
    }

    private Node<T> node(T element) {
        if (root == null) {
            return null;
        }
        Node<T> node = root;
        while (node != null) {
            int compare = compare(element, node.element);
            if (compare < 0) {//在左边
                node = node.left;
            } else if (compare > 0) {//在右边
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean contains(T element) {
        return node(element) != null;
    }

    private int compare(T e1, T e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<T>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(T element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null!");
        }
    }


}
