package com.jms.树;

import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/16 11:02
 */
public class _剑指_Offer_68_二叉搜索树的最近公共祖先 {

    TreeNode parent = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null){
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            }else if (node.val < p.val && node.val < q.val){
                node = node.right;
            }else break;
        }
        return node;
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val == q.val) return p;
        else if (p.val > q.val) {
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        findParent(root, p, q);

        return parent;
    }

    private void findParent(TreeNode node, TreeNode p, TreeNode q) {
        if (node.val == p.val || node.val == q.val) {
            parent = node;
            return;
        }
        if (node.val > p.val && node.val < q.val) {
            parent = node;
        } else if (node.val > p.val) {
            findParent(node.left, p, q);
        } else {
            findParent(node.right, p, q);
        }
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();

        findPath(root, p, stackP);
        findPath(root, q, stackQ);

        TreeNode parent = null;
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();
            if (nodeP == nodeQ) {
                parent = nodeP;
            } else {
                break;
            }
        }

        return parent;
    }

    private void findPath(TreeNode node, TreeNode target, Stack<TreeNode> stack) {
        if (target.val == node.val) {
            stack.push(node);
            return;
        } else if (target.val < node.val) {
            findPath(node.left, target, stack);
            stack.push(node);
        } else {
            findPath(node.right, target, stack);
            stack.push(node);
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
