package com.jms.树;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/25 10:43
 */
public class _297_二叉树的序列化与反序列化 {

    public static void main(String[] args) {
        Codec codec = new Codec();

        Codec.TreeNode root = new Codec.TreeNode(1);
        root.left = new Codec.TreeNode(2);

        Codec.TreeNode right = new Codec.TreeNode(3);
        root.right = right;
        right.left = new Codec.TreeNode(4);
        right.right = new Codec.TreeNode(5);


        String serialize = codec.serialize(root);
        System.out.println(serialize);

        Codec.TreeNode node = codec.deserialize(serialize);
        System.out.println(node);

    }

}

class Codec {

    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node = root;
        queue.add(node);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();

            if (poll.val == Integer.MAX_VALUE){
                builder.append("#,");
                continue;
            }else {
                String s = String.valueOf(poll.val);
                builder.append(s).append(",");
            }

            if (poll.left != null){
                queue.add(poll.left);
            }else {
                queue.add(new TreeNode(Integer.MAX_VALUE));
            }

            if (poll.right != null){
                queue.add(poll.right);
            }else {
                queue.add(new TreeNode(Integer.MAX_VALUE));
            }
        }

        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        if ("".equals(data)) return null;
        String substring = data.substring(0, data.length() - 1);

        List<String> list = Arrays.asList(substring.split(","));

        if (list.size() == 0) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        String s = list.get(0);
        TreeNode root = new TreeNode(Integer.parseInt(s));
        queue.add(root);
        int index = 1;

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (index == list.size()) break;
            String left = list.get(index++);
            if (!"#".equals(left)){
                node.left = new TreeNode(Integer.parseInt(left));
                queue.add(node.left);
            }
            if (index == list.size()) break;
            String right = list.get(index++);
            if (!"#".equals(right)){
                node.right = new TreeNode(Integer.parseInt(right));
                queue.add(node.right);
            }
        }

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
