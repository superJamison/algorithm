package com.jms.树;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/3 21:34
 */
public class _剑指_Offer_32_III从上到下打印二叉树 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)  return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // flag == true 从左往右
        boolean flag = true;

        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();

            int size = queue.size();
            if (flag){
                while (size-- > 0){
                    TreeNode node = queue.pollFirst();
                    list.add(node.val);
                    if (node.left != null) {
                        queue.addLast(node.left);
                    }
                    if (node.right != null){
                        queue.addLast(node.right);
                    }
                }
            }else {
                while (size-- > 0){
                    TreeNode node = queue.pollLast();
                    list.add(node.val);
                    if (node.right != null){
                        queue.addFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.addFirst(node.left);
                    }
                }
            }
            flag = !flag;

            result.add(list);
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
