package com.jms.队列;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/29 0:11
 */
public class _剑指_Offer_09_用两个栈实现队列 {

    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public static void main(String[] args) {
        priorityQueue.add(2);
        priorityQueue.add(3);
        priorityQueue.add(1);
        priorityQueue.add(7);
        priorityQueue.add(99);

        System.out.println(priorityQueue.poll());
    }

    private static void m1() {
        CQueue cQueue = new CQueue();

        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }
}

class CQueue {

    private Stack<Integer> headStack = null;
    private Stack<Integer> tailStack = null;

    public CQueue() {
        headStack = new Stack<>();
        tailStack = new Stack<>();
    }

    public void appendTail(int value) {
        tailStack.push(value);
    }

    public int deleteHead() {
        if (headStack.isEmpty()) {
            while (!tailStack.isEmpty()) {
                headStack.push(tailStack.pop());
            }
        }
        return headStack.isEmpty() ? -1 : headStack.pop();
    }

}
