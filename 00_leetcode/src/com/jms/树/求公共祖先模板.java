package com.jms.树;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/16 12:10
 */
public class 求公共祖先模板 {

    TreeNode result = null;
    int k;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        k = 2;
        dfs(root, p, q);
        return result;
    }

    private void dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || k == 0) return;
        int oldK = k;

        if (node.val == p.val || node.val == q.val) k--;
        dfs(node.left,p, q);
        dfs(node.right, p, q);

        // 核心：如果某节点遍历左右子树后k值由k变为0，表明该节点为所需节点。
        if (oldK == 2 && k == 0 && result == null){
            result = node;
        }
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
