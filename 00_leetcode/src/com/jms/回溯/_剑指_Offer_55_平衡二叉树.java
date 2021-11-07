package com.jms.回溯;

import javax.xml.soap.Node;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/15 17:48
 */
public class _剑指_Offer_55_平衡二叉树 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        return Math.abs(depth(root.left) - depth(root.right)) > 1 ? false : isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(TreeNode node) {
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
