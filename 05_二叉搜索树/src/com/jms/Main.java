package com.jms;

import com.jms.entity.Person;
import com.jms.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 21:31
 */
public class Main {

    private static class PersonComparator1 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return -e1.getAge() + e2.getAge();
        }
    }

    public static void test1(){
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
//        BinaryTrees.print(bst, BinaryTrees.PrintStyle.INORDER);
        BinaryTrees.print(bst, BinaryTrees.PrintStyle.LEVEL_ORDER);
    }

    private static void test2(){
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> personTree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            personTree.add(new Person(data[i], "Jamison"));
        }

        BinarySearchTree<Person> personTree2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            personTree.add(new Person(data[i], "Jamison"));
            personTree2.add(new Person(data[i], "Jamison"));
        }
        BinaryTrees.print(personTree);
        System.out.println();
        BinaryTrees.print(personTree2);
    }

    public static void test3(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 100; i++) {
            tree.add((int)(Math.random() * 100));
        }
        BinaryTrees.print(tree);
    }

    public static void test4(){
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 10, 3, 12, 1, 11
        };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\n层次遍历：");
        tree.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print("_" + integer + "_");return false;
                
            }
        });
        System.out.println("\n前序遍历：");
        tree.preOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print(integer + ", ");
                return false;
            }
        });
        System.out.println("\n中序遍历：");
        tree.inOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print(integer + ", ");
                return false;
            }
        });
        System.out.println("\n后序遍历：");
        tree.postOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print(integer + ", ");
                return false;
            }
        });
    }
    
    public static void test5(){
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 10, 3, 12, 1, 11
        };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\ntree的高度：" + tree.height());
        System.out.println("层次遍历：");
        tree.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print("_" + integer + "_");
                if (integer == 2){
                    return true;
                }
                return false;
            }
        });
        System.out.println("\n前序遍历：");
        tree.preOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print(integer + ", ");
                if (integer == 1){
                    return true;
                }
                return false;
            }
        });
        System.out.println("\n中序遍历：");
        tree.inOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print(integer + ", ");
                if (integer == 4){
                    return true;
                }
                return false;
            }
        });
        System.out.println("\n后序遍历：");
        tree.postOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.print(integer + ", ");
                if (integer == 5) {
                    return true;
                }
                return false;
            }
        });
    }

    private static void test6(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 20; i++) {
            tree.add((int)(Math.random() * 100));
        }
        BinaryTrees.print(tree);
        System.out.println("\ntree的高度：" + tree.height());
    }

    private static void test7(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//        for (int i = 0; i < 10; i++) {
//            tree.add(i + 1);
//        }
        /**
         *         7
         *     4       9
         *   3   5   8
         * 2
         */
        Integer[] treeNum = new Integer[]{
                7, 4, 9, 3, 5, 8, 10, 2
        };
        for (int i = 0; i < treeNum.length; i++) {
            tree.add(treeNum[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\n是否为完全二叉树：" + tree.isComplete());
    }

    private static void test8(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Integer[] treeNum = new Integer[]{
                7, 4, 9, 3, 5, 8, 10, 2
        };
        for (int i = 0; i < treeNum.length; i++) {
            tree.add(treeNum[i]);
        }
        BinaryTrees.print(tree);
        // 2 3 4 5 7 8 9 10
//        System.out.println("\n前驱：" + tree.predecessor());
    }

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
//        BinarySearchTree<Person> integerBinarySearchTree1 = new BinarySearchTree<Person>(new PersonComparator1());
//        BinarySearchTree<Person> integerBinarySearchTree2 = new BinarySearchTree<Person>();
        test9();
    }
}
