package com.jms.tree;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/26 23:16
 */
public class BBST<T> extends BST<T> {

    public BBST() {
        this(null);
    }

    public BBST(Comparator<T> comparator) {
        super(comparator);
    }

    protected void rotate(
            Node<T> r, //原先子树的根节点
            Node<T> a, Node<T> b, Node<T> c,
            Node<T> d,
            Node<T> e, Node<T> f, Node<T> g){
        //更新子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()){
            r.parent.left = d;
        }else if (r.isRightChild()){
            r.parent.right = d;
        }else {// r == root
            root = d;
        }

        // a-b-c
        b.left = a;
        if (a != null){
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    /**
     * 左旋转
     *
     * @param grand
     */
    protected void rotateLeft(Node<T> grand) {
        Node<T> parent = grand.right;
        Node<T> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     * @param grand
     */
    protected void rotateRight(Node<T> grand) {
        Node<T> parent = grand.left;
        Node<T> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<T> grand, Node<T> parent, Node<T> child){

        //更新parent的父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        //更新child的父节点
        if (child != null) {
            child.parent = grand;
        }

        //更新grand的父节点
        grand.parent = parent;
    }

}
