package com.jms.树;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/4 12:28
 */
public class _剑指_Offer_28_对称的二叉树 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return childNodeIsSymmetric(root.left, root.right);
    }

    private boolean childNodeIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;

        if (left == null || right == null || left.val != right.val) return false;

        return childNodeIsSymmetric(left.left, right.right) && childNodeIsSymmetric(left.right, right.left);
    }


}
