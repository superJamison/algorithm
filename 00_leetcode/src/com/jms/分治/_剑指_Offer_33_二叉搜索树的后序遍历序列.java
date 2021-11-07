package com.jms.分治;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/17 15:38
 */
public class _剑指_Offer_33_二叉搜索树的后序遍历序列 {

    public boolean verifyPostorder(int[] postorder) {

        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int i, int j) {
        if (i >= j) return true;

        int index = i;

        while (postorder[index] < postorder[j]) index++;

        // 记录第一个大于这棵树的根节点的索引
        int rightIndex = index;
        while (postorder[index] > postorder[j]) index++;

        return index == j && verify(postorder, i, rightIndex - 1) && verify(postorder, rightIndex, j -1);
    }
}
