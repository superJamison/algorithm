package com.jms.heap;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/31 21:06
 */
public interface Heap<T> {
    int size();
    boolean isEmpty();
    void clear();
    T get(); //获取堆顶元素
    void add(T element);
    T remove();
    T replace(T element);
}
