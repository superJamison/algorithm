package com.jms;

import com.jms.list.LinkedList;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 11:19
 */
public class Queue<T> {

    private LinkedList<T> linkedList = new LinkedList<>();

    public int size(){
        return linkedList.size();
    }

    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    public void enQueue(T element){
        linkedList.add(element);
    }

    public T deQueue(){
        return linkedList.remove(0);
    }

    public T front(){
        return linkedList.get(0);
    }

    public void clear(){
        linkedList.clear();
    }

    @Override
    public String toString() {
        return "Queue{" +
                "linkedList=" + linkedList +
                '}';
    }
}
