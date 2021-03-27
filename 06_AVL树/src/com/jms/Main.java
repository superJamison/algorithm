package com.jms;

import com.jms.printer.BinaryTrees;
import com.jms.tree.AVLTree;
import com.jms.tree.BST;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/26 13:01
 */
public class Main {

    public static void main(String[] args) {

        test();

//        Integer data[] = new Integer[]{
//                310, 95, 483, 995, 341, 714, 686, 624, 924, 969, 271
//        };
//        AVLTree<Integer> tree = new AVLTree<>();
//        for (int i = 0; i < data.length; i++) {
//            tree.add(data[i]);
////            System.out.println("【" + data[i] + "】");
////            BinaryTrees.print(tree);
////            System.out.println("\n------------------------");
//        }
//
//        for (int i = 0; i < data.length; i++) {
//            tree.remove(data[i]);
//            System.out.println("【" + data[i] + "】");
//            BinaryTrees.print(tree);
//            System.out.println("\n------------------------");
//        }
    }

    private static void test() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < 10_0000; i++) {
            lists.add((int) (Math.random() * 10_0000));
        }
        long l1 = System.currentTimeMillis();
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < lists.size(); i++) {
            bst.add(lists.get(i));
        }
        long l2 = System.currentTimeMillis();


        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < lists.size(); i++) {
            avlTree.add(lists.get(i));
        }
        long l3 = System.currentTimeMillis();


        System.out.println(l2 - l1);
        System.out.println(l3 - l2);
    }
}
