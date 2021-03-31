package com.jms.heap;

import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/31 21:57
 */
public abstract class AbstractHeap<T> implements Heap<T> {
    protected int size;
    protected Comparator<T> comparator;

    public AbstractHeap(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public AbstractHeap(){
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    protected int compare(T e1, T e2){
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<T>)e1).compareTo(e2);
    }
}
