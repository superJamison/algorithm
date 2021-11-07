package com.jms.树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/17 16:09
 */
public class _230_二叉搜索树中第K小的元素 {

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> queue = new LinkedList<>();

        while (!queue.isEmpty() || root != null){
            while (root != null){
                queue.push(root);
                root = root.left;
            }
            root = queue.pop();
            if (--k == 0){
                break;
            }
            root = root.right;
        }

        return root.val;
    }

    public class TreeNode {
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
