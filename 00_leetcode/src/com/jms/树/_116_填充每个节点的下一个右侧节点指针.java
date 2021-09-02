package com.jms.树;

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/24 22:09
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
 */
public class _116_填充每个节点的下一个右侧节点指针 {
    public static void main(String[] args) {
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);


        printTree(root);
    }

    private static void printTree(Node root) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.val);
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
    }

    /**
     * 最优解
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return root;

        Node leftMost = root;

        while (leftMost.left != null){
            Node parent = leftMost;

            while (parent != null){

                parent.left.next = parent.right;

                if (parent.next != null){
                    parent.right.next = parent.next.left;
                }

                parent = parent.next;
            }

            leftMost = leftMost.left;
        }

        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (i < size - 1){
                    node.next = queue.peek();
                }

                if (node.left != null){
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }

        return root;
    }

    public Node connect1(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node preNode = root;

        while (!queue.isEmpty()){
            Node node = queue.poll();
            preNode.next = node;
            preNode = node;
            if (node.left != null){
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        Node node = root;
        while (node != null){
            node.next = null;
            node = node.right;
        }

        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
