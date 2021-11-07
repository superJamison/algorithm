package com.jms.树;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/4 10:30
 */
public class _剑指_Offer_26_树的子结构 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfs(TreeNode poll, TreeNode b) {
        if (poll == null && b == null) return true;

        if (poll != null && b != null && poll.val == b.val) {
            if (b.left != null && b.right != null) {
                return dfs(poll.left, b.left) && dfs(poll.right, b.right);
            }
            if (b.left != null) {
                return dfs(poll.left, b.left);
            }
            if (b.right != null) {
                return dfs(poll.right, b.right);
            }
            return true;
        }

        return false;
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
