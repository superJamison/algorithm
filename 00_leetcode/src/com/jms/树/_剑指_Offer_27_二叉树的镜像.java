package com.jms.树;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/4 11:31
 */
public class _剑指_Offer_27_二叉树的镜像 {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null)  return null;

        TreeNode left = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = left;

        return root;
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
