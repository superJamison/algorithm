package com.jms.树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/12 20:05
 */
public class _剑指_Offer_34_二叉树中和为某一值的路径 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        TreeNode node1 = new TreeNode(11);
        root.left.left = node1;
        node1.left = new TreeNode(7);
        node1.right = new TreeNode(2);

        root.right = new TreeNode(8);

        TreeNode treeNode1 = new TreeNode(13);
        TreeNode treeNode2 = new TreeNode(4);
        root.right.left = treeNode1;
        root.right.right = treeNode2;

        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(1);
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        _剑指_Offer_34_二叉树中和为某一值的路径 v = new _剑指_Offer_34_二叉树中和为某一值的路径();
        List<List<Integer>> lists = v.pathSum(root, 22);

        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        dfs(root, target, result, list, 0);

        return result;
    }

    private void dfs(TreeNode node, int target, List<List<Integer>> result, List<Integer> list, int sum) {
        if (node == null) return;

        if (node.left != null) {
            sum += node.val;
            list.add(node.val);
            dfs(node.left, target, result, list, sum);
            list.remove(list.size() - 1);
            sum -= node.val;
        }
        if (node.right != null) {
            sum += node.val;
            list.add(node.val);
            dfs(node.right, target, result, list, sum);
            list.remove(list.size() - 1);
            sum -= node.val;
        }
        if (node.left == null && node.right == null){
            sum += node.val;
            if (sum == target) {
                list.add(node.val);
                List<Integer> resultList = new ArrayList<>(list.size());
                resultList.addAll(list);

                result.add(resultList); // 添加到result结果集中
                list.remove(list.size() - 1);
            }
        }
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
