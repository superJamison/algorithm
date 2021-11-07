package com.jms.排序算法;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/14 17:16
 */
public class _剑指_Offer_41_数据流中的中位数 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

//        medianFinder.addNum(1);
//        medianFinder.addNum(2);
//        System.out.println(medianFinder.findMedian());
//        medianFinder.addNum(3);
//        System.out.println(medianFinder.findMedian());
//        medianFinder.addNum(4);
//        System.out.println(medianFinder.findMedian());

        medianFinder.addNum(6);
        medianFinder.addNum(10);
        medianFinder.addNum(2);
        medianFinder.addNum(6);
        medianFinder.addNum(5);
        medianFinder.addNum(0);
        medianFinder.addNum(6);
        medianFinder.addNum(3);
        medianFinder.addNum(1);
        medianFinder.addNum(0);
        medianFinder.addNum(0);
    }
}

class MedianFinder {

    List<Integer> list;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(getIndex(num), num);
    }

    private int getIndex(int num) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (num <= list.get(mid)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public double findMedian() {
        int size = list.size();
        int mid = size >> 1;
        return (size & 1) == 0 ? (list.get(mid) + list.get(mid - 1)) / 2.0 :  list.get(mid) / 1.0;
    }
}

class MedianFinder2 {

    PriorityQueue<Integer> queueA;
    PriorityQueue<Integer> queueB;

    /**
     * initialize your data structure here.
     */
    public MedianFinder2() {
        queueA = new PriorityQueue<>();
        queueB = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        if (queueA.size() == queueB.size()) {
            queueB.add(num);
            queueA.add(queueB.poll());
        } else {
            queueA.add(num);
            queueB.add(queueA.poll());
        }
    }

    public double findMedian() {
        return queueA.size() == queueB.size() ? (queueA.peek() + queueB.peek()) / 2.0 : queueA.peek() / 1.0;
    }
}

class MedianFinder1 {

    List<Integer> list;

    /**
     * initialize your data structure here.
     */
    public MedianFinder1() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {
        // 对list进行快速排序
        quickSort(0, list.size() - 1);

        int size = list.size();
        if ((size & 1) == 0) {
            // 偶数个数
            return (list.get(size >> 1) + list.get((size >> 1) - 1)) / 2.0;
        } else {
            // 奇数个数
            return list.get(size >> 1) / 1.0;
        }
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int index = getIndex(low, high);
            quickSort(low, index - 1);
            quickSort(index + 1, high);
        }
    }

    private int getIndex(int low, int high) {
        int temp = list.get(low);

        while (low < high) {
            while (low < high && list.get(high) >= temp) high--;
            list.set(low, list.get(high));

            while (low < high && list.get(low) <= temp) low++;
            list.set(high, list.get(low));
        }
        list.set(low, temp);
        return low;
    }
}
