package com.jms.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/8 9:27
 */
public class _144_二叉树的前序遍历 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        List<Integer> list = preorderTraversal(root);
        list.forEach(x -> {
            System.out.println(x);
        });
    }

    // 迭代方式
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(node);

        while (!stack.isEmpty() && (node = stack.pop()) != null){
            list.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return list;
    }


    // 递归方式
//    public static List<Integer> preorderTraversal(TreeNode root) {
//        ArrayList<Integer> list = new ArrayList<>();
//        preOrder(root, list);
//        return list;
//    }
//
//    public static void preOrder(TreeNode node, List<Integer> list){
//        if (node == null) return;
//
//        list.add(node.val);
//        preOrder(node.left, list);
//        preOrder(node.right, list);
//    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


