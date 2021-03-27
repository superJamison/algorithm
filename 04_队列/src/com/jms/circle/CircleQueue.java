package com.jms.circle;

import java.util.Arrays;

/**
 * 循环队列
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 16:24
 */
public class CircleQueue<T> {

    private int front; //队头
    private int size;
    private T[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    public void enQueue(T element) {
        //实现自动扩容
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }



    /**
     * 可以增加判断队列是否为空，自定义的抛出异常信息，
     * 但是也不用判断，这样就是java内部抛出异常
     *
     * @return
     */
    public T deQueue() {
        T frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    public T front() {
        return elements[front];
    }

    public int index(int index){
//        return (index + front) % elements.length;
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    /**
     * 保证数组的容量，如果容量不够了，就扩容位原来的1.5倍
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity <= oldCapacity) return;

        //新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newElements = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        //重置front
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("front:").append(front)
                .append(", capacity:").append(elements.length)
                .append(", size:").append(size).append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i == 0) {
                builder.append(elements[i]);
            } else {
                builder.append(", ").append(elements[i]);
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
