package com.jms;

import com.jms.printer.BinaryTreeInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 21:31
 */
public class BinarySearchTree<T> implements BinaryTreeInfo {

    private int size;
    private Node<T> root;
    private Comparator<T> comparator;

    private static class Node<T> {
        T element;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        public Node(T element, Node<T> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 判断是否为叶子节点
         *
         * @return 返回值  true:是叶子节点   false：不是叶子节点
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断是否有两个叶子节点
         *
         * @return 返回值：true:有两个叶子节点  false：没有两个叶子节点
         */
        public boolean hasTwoLeaf() {
            return left != null && right != null;
        }
    }

    public static abstract class Visitor<T> {
        boolean stop;

        abstract boolean visit(T t);
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void add(T element) {
        elementNotNullCheck(element);
        //添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
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

        Node<T> newNode = new Node<>(element, parentNode);
        //看看插入的节点位置是在那个位置
        if (compare > 0) {
            parentNode.right = newNode;
        } else if (compare < 0) {
            parentNode.left = newNode;
        }
        size++;
    }

    /**
     * 前序遍历
     */
    public void preOrderTraversal(Visitor<T> visitor) {
        if (visitor == null) {
            return;
        }
        preOrderTraversal(root, visitor);
    }

    private void preOrderTraversal(Node<T> node, Visitor<T> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preOrderTraversal(node.left, visitor);
        preOrderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inOrderTraversal(Visitor<T> visitor) {
        if (visitor == null) {
            return;
        }
        inOrderTraversal(root, visitor);
    }

    private void inOrderTraversal(Node<T> node, Visitor<T> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inOrderTraversal(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inOrderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postOrderTraversal(Visitor<T> visitor) {
        if (visitor == null) {
            return;
        }
        postOrderTraversal(root, visitor);
    }

    private void postOrderTraversal(Node<T> node, Visitor<T> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postOrderTraversal(node.left, visitor);
        postOrderTraversal(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层次遍历
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.print(node.element + ", ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 层次遍历
     *
     * @param visitor
     */
    public void levelOrder(Visitor<T> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
//            System.out.print(node.element + ", ");

            boolean stop = visitor.visit(node.element);
            if (stop) {
                return;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 获取tree的高度
     *
     * @return 返回高度
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        //数的高度
        int height = 0;
        //数的每一层有多少个元素
        int levelHeight = 1;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            levelHeight--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            //这一层的元素即将遍历完成，即将进入下一层进行遍历
            if (levelHeight == 0) {
                levelHeight = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 判断是否为完全二叉树
     *
     * @return 返回值 true：是完全二叉树  false：不是完全二叉树
     */
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { //node.left == null
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else { //node.right == null 代表后面遍历的节点都是叶子结点
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 查找指定节点的前驱
     *
     * @param node
     * @return
     */
    public Node<T> predecessor(Node<T> node) {
        if (node == null) return null;

        Node<T> p = node.left;
        //左子树不为空，前驱节点在左子树上
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        //从父节点，祖父节点中找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }

    /**
     * 查找指定节点的后继
     *
     * @param node
     * @return
     */
    public Node<T> successor(Node<T> node) {
        if (node == null) return null;

        Node<T> s = node.right;
        //左子树不为空，前驱节点在左子树上
        if (s != null) {
            while (s.left != null) {
                s = s.left;
            }
            return s;
        }

        //从父节点，祖父节点中找后继节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }

    public int height1() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

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

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<T>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<T>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<T> myNode = (Node<T>) node;
        String parentStr = "null";
        if (myNode.parent != null) {
            parentStr = myNode.parent.element.toString();
        }
        return ((Node<T>) node).element + "_p(" + parentStr + ")";
    }

//    /**
//     * 前序遍历
//     */
//    public void preOrderTraversal(){
//        preOrderTraversal(root);
//    }
//
//    private void preOrderTraversal(Node<T> node){
//        if (node == null){
//            return;
//        }
//        System.out.print(node.element + ", ");
//        preOrderTraversal(node.left);
//        preOrderTraversal(node.right);
//    }
//
//    /**
//     * 中序遍历
//     */
//    public void inOrderTraversal(){
//        inOrderTraversal(root);
//    }
//
//    private void inOrderTraversal(Node<T> node){
//        if (node == null){
//            return;
//        }
//        inOrderTraversal(node.left);
//        System.out.print(node.element + ", ");
//        inOrderTraversal(node.right);
//    }
//
//    /**
//     * 后序遍历
//     */
//    public void postOrderTraversal(){
//        inOrderTraversal(root);
//    }
//
//    private void postOrderTraversal(Node<T> node){
//        if (node == null){
//            return;
//        }
//        postOrderTraversal(node.left);
//        postOrderTraversal(node.right);
//        System.out.print(node.element + ", ");
//    }

    /**
     * 判断是否为完全二叉树
     * @return 返回值 true：是完全二叉树  false：不是完全二叉树
     */
    /*
    public boolean isComplete(){
        if (root == null) return false;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<T> node = queue.poll();
            if (leaf && !node.isLeaf()){
                return false;
            }
            if (node.hasTwoLeaf()){
                queue.offer(node.left);
                queue.offer(node.right);
            }else if (node.left == null && node.right != null){
                return false;
            }else {// 代表后面遍历的节点都必须是叶子结点
                leaf = true;
                if (node.left != null){
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }*/
}
