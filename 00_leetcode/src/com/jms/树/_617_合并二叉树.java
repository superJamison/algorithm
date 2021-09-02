package com.jms.树;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/24 20:29
 */
public class _617_合并二叉树 {
    public static void main(String[] args) {
        _617_合并二叉树 v = new _617_合并二叉树();

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode treeNode = v.mergeTrees(root1, root2);

        printTree(treeNode);
    }

    private static void printTree(TreeNode node) {
        if (node.left != null){
            printTree(node.left);
        }
        System.out.println(node.val);
        if (node.right != null){
            printTree(node.right);
        }
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode root = merge(root1, root2);
        return root;
    }

    private TreeNode merge(TreeNode node1, TreeNode node2) {

        if (node1 == null) return node2;
        if (node2 == null) return node1;

        node1.val = node1.val + node2.val;
        // 合并左子节点
        TreeNode left = merge(node1.left, node2.left);
        if (left != null){
            node1.left = left;
        }
        // 合并右子节点
        TreeNode right = merge(node1.right, node2.right);
        if (right != null){
            node1.right = right;
        }
        return node1;
    }

    static class TreeNode {
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



