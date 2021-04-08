package com.jms;

import com.jms.sort.*;
import com.jms.sort.cmp.*;
import com.jms.tools.Asserts;
import com.jms.tools.Integers;
import com.jms.tools.Times;

import java.util.Arrays;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 19:55
 *
 * 最坏、平均时间复杂度：O(n^2)
 * 最好时间复杂度；O(n)
 * 空间复杂度：O(1)
 *
 * 是否稳定：稳定
 * 原地算法
 *
 * 选择排序的数据交换次数低于冒泡排序
 */
public class Main {

    public static void main(String[] args) {
//        test();

//        test1();

        Integer[] arrays = Integers.random(10000, 1, 1000000);
//        Integer[] arrays = {7,3,5,8,6,7,4,5};
//        Integer[] arrays = {126,69,593,23,6,89,54,8};

        sort(arrays,
                new HeadSort<Integer>(),
//                new CountingSort()
//                new RadixSort()
                new SelectionSort<Integer>(),
//                new InsertionSort1<Integer>(),
//                new InsertionSort2<Integer>(),
                new InsertionSort3<Integer>(),
                new MergeSort<Integer>(),
                new ShellSort<>(),
                new QuickSort<Integer>(),
//                new BubbleSort<Integer>(),
//                new BubbleSort1<Integer>(),
                new BubbleSort2<Integer>()
        );
    }

    private static void sort(Integer[] arrays, Sort<Integer>... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(arrays));
        }

        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

    private static void test1() {
        Integer[] arrays1 = Integers.random(10000, 1, 20000);
        Integer[] arrays2 = Integers.copy(arrays1);
        Integer[] arrays3 = Integers.copy(arrays1);
        Times.test("HeadSort", () -> {
            new HeadSort().sort(arrays1);
        });
        Times.test("SelectionSort", () -> {
            new SelectionSort().sort(arrays3);
        });
        Times.test("BubbleSort2", () -> {
            new BubbleSort2().sort(arrays2);
        });
    }

    private static void test() {
        //        Integer[] arrays1 = Integers.random(100000, 1, 100000);
//        Integer[] arrays1 = Integers.ascOrder(1, 100000);
//        Integer[] arrays1 = Integers.tailAscOrder(1, 20000, 4000);
//        Integer[] arrays2 = Integers.copy(arrays1);
//        Integer[] arrays3 = Integers.copy(arrays1);
//        Times.test("bubbleSort1", () -> {
//            bubbleSort1(arrays1);
//        });
//
//        Times.test("bubbleSort2", () -> {
//            bubbleSort2(arrays2);
//        });
//
//        Times.test("bubbleSort3", () -> {
//            bubbleSort3(arrays3);
//        });

        Integer[] arrays1 = Integers.random(1000, 1, 1000);
        selectionSort1(arrays1);
        Asserts.test(Integers.isAscOrder(arrays1));
//        Times.test("selectionSort1", () -> {
//             selectionSort1(arrays1);
//        });
//
//        Times.test("bubbleSort1", () -> {
//             bubbleSort1(arrays1);
//        });
    }


    //选择排序
    private static void selectionSort1(Integer[] arrays) {
        for (int end = arrays.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int bedin = 1; bedin <= end; bedin++) {
                if (arrays[maxIndex] <= arrays[bedin]){
                    maxIndex = bedin;
                }
            }
            Integer tmp = arrays[maxIndex];
            arrays[maxIndex] = arrays[end];
            arrays[end] = tmp;
        }

//        Integers.println(arrays);
    }

    //冒泡排序
    private static void bubbleSort1(Integer[] arrays) {
        for (int end = arrays.length - 1; end > 0; end--) {
            for (int start = 1; start <= end; start++) {
                if (arrays[start] < arrays[start - 1]) {
                    Integer tmp = arrays[start];
                    arrays[start] = arrays[start - 1];
                    arrays[start - 1] = tmp;
                }
            }
        }

//        for (int i = 0; i < arrays.length; i++) {
//            System.out.print(arrays[i] + "_");
//        }
    }

    private static void bubbleSort2(Integer[] arrays) {
        for (int end = arrays.length - 1; end > 0; end--) {
            boolean flag = true;
            for (int start = 1; start <= end; start++) {
                if (arrays[start] < arrays[start - 1]) {
                    Integer tmp = arrays[start];
                    arrays[start] = arrays[start - 1];
                    arrays[start - 1] = tmp;
                    flag = false;
                }
            }
            if (flag) break;
        }

//        for (int i = 0; i < arrays.length; i++) {
//            System.out.print(arrays[i] + "_");
//        }
    }

    private static void bubbleSort3(Integer[] arrays) {
        for (int end = arrays.length - 1; end > 0; end--) {
            int sortedIndex = 0;
            for (int start = 1; start <= end; start++) {
                if (arrays[start] < arrays[start - 1]) {
                    Integer tmp = arrays[start];
                    arrays[start] = arrays[start - 1];
                    arrays[start - 1] = tmp;
                    sortedIndex = start;
                }
            }
            end = sortedIndex;
        }

//        for (int i = 0; i < arrays.length; i++) {
//            System.out.print(arrays[i] + "_");
//        }
    }
}
