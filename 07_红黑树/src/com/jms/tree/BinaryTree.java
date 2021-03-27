package com.jms.tree;

import com.jms.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/25 18:57
 */
public class BinaryTree<T> implements BinaryTreeInfo {

    protected int size;
    protected Node<T> root;

    protected static class Node<T> {
        public T element;
        public Node<T> parent;
        public Node<T> left;
        public Node<T> right;

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

        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }

        public Node<T> sibling(){
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    public static abstract class Visitor<T> {
        boolean stop;

        protected abstract boolean visit(T t);
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

    protected Node<T> createNode(T element, Node<T> parentNode){
        return new Node<>(element, parentNode);
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
     * @return 返回高度
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        //树的高度
        int height = 0;
        //树的每一层有多少个元素
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
     * 查找指定节点的前驱
     *
     * @param node
     * @return
     */
    protected Node<T> predecessor(Node<T> node) {
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
     * @param node
     * @return
     */
    protected Node<T> successor(Node<T> node) {
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

    /**
     * 判断是否为完全二叉树
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

    public int height1() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
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
        return node;
    }
}
