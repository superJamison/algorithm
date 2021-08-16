package com.jms.树;

import com.sun.deploy.panel.ITreeNode;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/8 16:27
 */
public class _145_二叉树的后序遍历 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> list = postorderTraversal(root);

        list.forEach(x -> {
            System.out.println(x);
        });
    }

    // 迭代
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode prev = null;

        while (!stack.isEmpty() || node != null) {
            // 找到最左节点
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // 没有右节点或者是之前已经遍历过了
            if (node.right == null || node.right == prev){
                list.add(node.val);
                prev = node;
                node = null;
            }
            // 右节点后序遍历
            else {
                stack.push(node);
                node = node.right;
            }
        }

        return list;
    }

    // 递归
//    public static List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//
//        postOrder(root, list);
//
//        return list;
//    }
//
//    public static void postOrder(TreeNode node, List<Integer> list){
//        if (node == null) return;
//
//        if (node.left != null) postOrder(node.left, list);
//        if (node.right != null) postOrder(node.right, list);
//        list.add(node.val);
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
