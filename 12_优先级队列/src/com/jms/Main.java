package com.jms;

import com.jms.entity.Person;
import com.jms.queue.PriorityQueue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/1 20:15
 */
public class Main {

    public static void main(String[] args) {
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.enQueue(new Person("Jamison", 10));
        queue.enQueue(new Person("SuperJamison", 33));
        queue.enQueue(new Person("zms", 16));
        queue.enQueue(new Person("Jack", 12));
        queue.enQueue(new Person("Rose", 4));
        while (!queue.isEmpty()){
            System.out.println("front--->" + queue.front());
            System.out.println("deQueue--->" +queue.deQueue());
        }
    }
}
