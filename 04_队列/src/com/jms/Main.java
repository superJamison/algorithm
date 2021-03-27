package com.jms;

import com.jms.circle.CircleDeque;
import com.jms.circle.CircleQueue;
import javafx.scene.shape.Circle;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 11:19
 */
public class Main {

    public static void main(String[] args) {
        CircleDeque<Integer> circleDeque = new CircleDeque<>();
        for (int i = 1; i <= 10; i++) {
            circleDeque.enQueueRear(i);
        }
        System.out.println(circleDeque);
        for (int i = 0; i < 5; i++) {
            System.out.println(circleDeque.deQueueRear());
        }
        System.out.println(circleDeque);
        for (int i = 15; i < 23; i++) {
            circleDeque.enQueueFront(i);
        }
        System.out.println(circleDeque);
        while (!circleDeque.isEmpty()){
            System.out.println(circleDeque.deQueueFront());
        }
    }

    public void test1(){
        // 头 66 44 33 11 22 55   尾
        MyDeque<Integer> deque = new MyDeque<>();
        deque.enQueueFront(11);
        deque.enQueueRear(22);
        deque.enQueueFront(33);
        deque.enQueueFront(44);
        deque.enQueueRear(55);
        deque.enQueueFront(66);
        System.out.println(deque);
        while (!deque.isEmpty()){
            System.out.println(deque.deQueueFront());

        }
    }

    public void test(){
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        System.out.println(queue);
        if (!queue.isEmpty()){
            System.out.println(queue.deQueue());
            System.out.println(queue);
        }
    }
}
