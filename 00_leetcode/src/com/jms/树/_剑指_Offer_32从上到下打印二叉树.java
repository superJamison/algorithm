package com.jms.树;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/3 20:56
 */
public class _剑指_Offer_32从上到下打印二叉树 {

    public static void main(String[] args) {

    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
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
