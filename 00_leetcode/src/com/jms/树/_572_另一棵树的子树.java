package com.jms.树;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/8 23:41
 *
 */
public class _572_另一棵树的子树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        boolean subtree = isSubtree(root, subRoot);
        System.out.println(subtree);
    }

    /**
     * 暴力查找法
     * 判断一个树（subRoot）是否是给定树（root）的子树的条件：
     *              要么是两颗树相同，
     *              要么是给定树的左子树（root.left）与subRoot相同，
     *              要么是给定树的右子树（root.right）与subRoot相同，
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (subRoot == null) return true;

        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /**
     * 判断两颗树是否完全相等
     */
    private static boolean isSameTree(TreeNode root, TreeNode subRoot) {
        // 两颗树都为null，则相同
        if (root == null && subRoot == null) return true;
        // 一颗树为空，一颗树不为空，则代表两颗树不相同
        if (root == null || subRoot == null) return false;
        // 两颗树的值不相等，则两颗树不相同
        if (root.val != subRoot.val) return false;

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }

    public static class TreeNode {
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
