package com.jms.树;

import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/24 22:40
 */
public class _226_翻转二叉树 {

//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) {
//            return root;
//        }
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//        invertTree(root.left);
//        invertTree(root.right);
//        return root;
//    }

    public TreeNode invertTree(TreeNode root){
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }

}
