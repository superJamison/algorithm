package com.jms;

import com.jms.printer.BinaryTrees;
import com.jms.tree.AVLTree;
import com.jms.tree.BST;
import com.jms.tree.BinaryTree;
import com.jms.tree.RBTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/27 0:11
 */
public class Main {

    public static void main(String[] args) {
        test1();
    }

    private static void test() {

        Integer data[] = new Integer[]{
                79, 31, 39, 91, 46, 60, 48, 36, 33, 42, 20, 86, 45
        };
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.print(tree);
    }

    private static void test1() {

        Integer data[] = new Integer[]{
                79, 31, 39, 91, 46, 60, 48, 36, 33, 42, 20, 86, 45
        };
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.print(tree);
        for (int i = 0; i < data.length; i++) {
            System.out.println("\n-----------------------");
            tree.remove(data[i]);
            System.out.println("【" + data[i] + "】");
            BinaryTrees.print(tree);
        }

    }
}
