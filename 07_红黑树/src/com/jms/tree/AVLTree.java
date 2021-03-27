package com.jms.tree;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/25 20:35
 */
public class AVLTree<T> extends BBST<T> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<T> comparator) {
        super(comparator);
    }

    private boolean isBalance(Node<T> node) {
        return Math.abs(((AVLNode<T>) node).balanceFactor()) <= 1;
    }

    protected static class AVLNode<T> extends Node<T> {
        int height = 1;

        public AVLNode(T element, Node<T> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<T>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<T>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<T>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<T>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<T> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<T>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<T>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentStr = "null";
            if (this.parent != null) {
                parentStr = parent.element.toString();
            }
            return element + "_p(" + parentStr + ")" + "_h(" + height + ")";
        }
    }

    @Override
    protected void afterAdd(Node<T> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                reBalance(node);
                //整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<T> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                reBalance(node);
            }
        }
    }

    /**
     * 恢复平衡
     *
     * @param grand
     */
    private void reBalance1(Node<T> grand) {
        Node<T> parent = ((AVLNode<T>) grand).tallerChild();
        Node<T> node = ((AVLNode<T>) parent).tallerChild();
        if (parent.isLeftChild()) {// L
            if (node.isLeftChild()) {// LL
                rotateRight(grand);
            } else {// LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {//R
            if (node.isLeftChild()) {// RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {// RR
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复平衡
     *
     * @param grand
     */
    private void reBalance(Node<T> grand) {
        Node<T> parent = ((AVLNode<T>) grand).tallerChild();
        Node<T> node = ((AVLNode<T>) parent).tallerChild();
        if (parent.isLeftChild()) {// L
            if (node.isLeftChild()) {// LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {// LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {//R
            if (node.isLeftChild()) {// RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {// RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void rotate(Node<T> r, Node<T> a, Node<T> b, Node<T> c, Node<T> d, Node<T> e, Node<T> f, Node<T> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        //更新节点的高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    @Override
    protected void afterRotate(Node<T> grand, Node<T> parent, Node<T> child) {
        super.afterRotate(grand, parent, child);
        //更新节点高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(Node<T> node) {
        ((AVLNode<T>) node).updateHeight();
    }

    @Override
    protected Node<T> createNode(T element, Node<T> parentNode) {
        return new AVLNode<>(element, parentNode);
    }

}
