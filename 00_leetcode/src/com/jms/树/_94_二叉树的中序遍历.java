package com.jms.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/8 11:33
 */
public class _94_二叉树的中序遍历 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);

        List<Integer> list = inorderTraversal(root);

        list.forEach(x -> {
            System.out.println(x);
        });
    }

    // 迭代
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(node);

        while (!stack.isEmpty()) {
            while (node.left != null){
                stack.push(node.left);
                node = node.left;
            }
            while (!stack.isEmpty()){
                node = stack.pop();
                list.add(node.val);
                if (node.right != null){
                    stack.push(node.right);
                    node = node.right;
                    break;
                }
            }
        }

        return list;
    }

    // 递归实现
//    public static List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        inOrder(root, list);
//        return list;
//    }
//
//    public static void inOrder(TreeNode node, List<Integer> list){
//        if (node == null) return;
//
//        if (node.left != null) inOrder(node.left, list);
//        list.add(node.val);
//        if (node.right != null) inOrder(node.right, list);
//    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
