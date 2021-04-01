package com.jms.queue;

import com.jms.heap.BinaryHeap;
import com.jms.heap.Heap;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/1 20:16
 */
public class PriorityQueue<T> {

    private Heap<T> heap;

    public PriorityQueue(Comparator<T> comparator){
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue(){
        this(null);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public void clear(){
        heap.clear();
    }

    public void enQueue(T element){
        heap.add(element);
    }

    public T deQueue(){
        return heap.remove();
    }

    public T front(){
        return heap.get();
    }


}
