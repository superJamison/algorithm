package com.jms.树;

import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/12 20:42
 */
public class _剑指_Offer_36_二叉搜索树与双向链表 {

    Node head, tail, prev;

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;

        dfs(root);

        head.left = tail;
        tail.right = head;

        return head;
    }

    private void dfs(Node node) {
        if (node == null) return;

        dfs(node.left);

        // 最左边的节点，就是最小的那个节点。
        if (prev == null){
            head = node;
        }else {
            // 处于中间的节点
            prev.right = node;
        }
        node.left = prev;
        // 保存前面一个节点
        prev = node;
        tail = node;

        dfs(node.right);
    }

    // 非递归
    public Node treeToDoublyList1(Node root) {
        if (root == null) return root;

        Stack<Node> stack = new Stack<>();
        Node node = root;

        while (!stack.isEmpty() || node != null){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            // 访问节点
            node = stack.pop();
            if (prev == null) head = node;
            else prev.right = node;
            node.left = prev;
            prev = node;
            node = node.right;
        }

        head.left = prev;
        prev.right = head;

        return head;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
