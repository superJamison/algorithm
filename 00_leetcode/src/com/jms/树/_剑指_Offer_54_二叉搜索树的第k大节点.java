package com.jms.树;

import sun.security.krb5.internal.rcache.DflCache;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/12 21:11
 */
public class _剑指_Offer_54_二叉搜索树的第k大节点 {

    private int k;

    public int kthLargest(TreeNode root, int k) {
        TreeNode result = new TreeNode(0);
        this.k = k;
        dfs(root, result);
        return result.val;
    }

    // 右 中 左
    private void dfs(TreeNode node, TreeNode result) {
        if (node == null) return;
        dfs(node.right,result);
        if (--k == 0) {
            result.val = node.val;
            return;
        }
        dfs(node.left, result);
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
