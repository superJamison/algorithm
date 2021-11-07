package com.jms.树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/17 13:57
 */
public class _剑指_Offer_07_重建二叉树 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildChilds(preorder, inorder, map, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode buildChilds(int[] preorder, int[] inorder, Map<Integer, Integer> map, int preLeft, int preRight, int inLeft, int inRinght) {
        if (preLeft > preRight) return null;

        // 找出根节点
        int rootValue = preorder[preLeft];
        TreeNode node = new TreeNode(rootValue);

        int index = map.get(rootValue);
        int leftSize = index - inLeft;

        node.left = buildChilds(preorder, inorder, map, preLeft + 1, preLeft + leftSize,inLeft, index - 1);
        node.right = buildChilds(preorder,inorder,map,preLeft + leftSize + 1,preRight,index + 1, inRinght);

        return node;
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
