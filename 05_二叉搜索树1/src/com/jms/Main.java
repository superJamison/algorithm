package com.jms;

import com.jms.printer.BinaryTrees;
import com.jms.tree.BinarySearchTree;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 21:31
 */
public class Main {

    public static void test9(){
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.print(tree);
        tree.remove(7);
        System.out.println();
        BinaryTrees.print(tree);
    }

    public static void main(String[] args) {
        test9();
    }
}
