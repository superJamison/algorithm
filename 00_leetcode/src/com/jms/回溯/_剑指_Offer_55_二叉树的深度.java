package com.jms.回溯;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/15 17:40
 */
public class _剑指_Offer_55_二叉树的深度 {

    public static void main(String[] args) {

    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return depth(root);
    }

    // 求node节点的深度
    private int depth(TreeNode node) {
        if (node == null) return 0;

        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
