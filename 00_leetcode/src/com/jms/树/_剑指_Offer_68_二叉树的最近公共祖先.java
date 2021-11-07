package com.jms.树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/16 11:41
 */
public class _剑指_Offer_68_二叉树的最近公共祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        return l == null ? r : (r == null ? l : root);
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Deque<TreeNode> queueP = new LinkedList<>();
        Deque<TreeNode> queueQ = new LinkedList<>();

        findPath(root, p, queueP);
        findPath(root, q, queueQ);

        TreeNode parent = root;
        while (!queueP.isEmpty() && !queueQ.isEmpty()) {
            TreeNode nodeP = queueP.pollLast();
            TreeNode nodeQ = queueQ.pollLast();

            if (nodeP.val == nodeQ.val) parent = nodeP;
            else break;
        }

        return parent;
    }

    private void findPath(TreeNode node, TreeNode target, Deque<TreeNode> deque) {
        if (node == null) return;
        if (node.val == target.val) {
            deque.push(node);
            return;
        }
        deque.push(node);
        findPath(node.left, target, deque);
        if (deque.peek().val == target.val) {
            return;
        } else {
            deque.remove(node);
        }
        deque.push(node);
        findPath(node.right, target, deque);
        if (deque.peek().val == target.val) {
            return;
        } else {
            deque.remove(node);
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
