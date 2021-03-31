package com.jms;

import com.jms.heap.BinaryHeap;
import com.jms.heap.Heap;
import com.jms.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/31 21:05
 */
public class Main {
    public static void main(String[] args) {
//        test1();

//        test2();

//        test3();

        test4();
    }

    /**
     * Top k 问题
     */
    private static void test4() {
        //新建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>( new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 找出最大的前k个数字
        int k = 4;
        Integer[] data = {31, 8, 22, 84, 13, 3, 70, 55, 75, 47, 19, 12, 87, 99, 52, 39, 37, 41};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k ){
                heap.add(data[i]);
            }else if (data[i] > heap.get()){
                heap.replace(data[i]);
            }
        }
        BinaryTrees.print(heap);
    }

    private static void test3() {
        Integer[] data = {9, 86, 11, 34, 92, 30, 59, 22, 73, 20, 81, 39, 94, 40};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        BinaryTrees.print(heap);
    }

    private static void test2() {
        Integer[] data = {9, 86, 11, 34, 92, 30, 59, 22, 73, 20, 81, 39, 94, 40};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        BinaryTrees.print(heap);
    }


    private static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(90);
        heap.add(10);
        heap.add(65);
        BinaryTrees.print(heap);
//        heap.remove();
//        System.out.println();
//        BinaryTrees.print(heap);
        System.out.println(heap.replace(70));
        BinaryTrees.print(heap);
    }
}
