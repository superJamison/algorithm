package com.jms.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/8 22:05
 */
public class _117_填充每个节点的下一个右侧节点指针II {

    public static void main(String[] args) {
        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, null, new Node(7), null);
        Node root = new Node(1, left, right, null);

        Node connect = connect(root);

        System.out.println(connect);
    }

    public static Node connect(Node root) {
        if (root == null) return root;

        // 每一层的最左边的节点
        Node leftNode = root;

        while (leftNode != null){
            Node parent = leftNode;

            while (parent != null){
                if (parent.left != null){
                    if (parent.right != null){
                        parent.left.next = parent.right;
                    }else {
                        parent.left.next = findNext(parent.next);
                    }
                }
                if (parent.right != null){
                    parent.right.next = findNext(parent.next);
                }
                parent = parent.next;
            }

            // 寻找下一层的最左边的节点
            leftNode = findNext(leftNode);
        }

        return root;
    }

    /**
     * 寻找下一个节点
     * @param next
     * @return
     */
    private static Node findNext(Node next) {
        while (next != null){
            if (next.left != null){
                return next.left;
            }else if (next.right != null){
                return next.right;
            }else {
                next = next.next;
            }
        }

        return null;
    }

    /**
     * 利用队列逐层查找next节点，空间复杂度：O(n)
     */
    public static Node connect1(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node node;
        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (i < size - 1){
                    node.next = queue.peek();
                }

                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }

        }

        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }

        public Node() {
        }

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
