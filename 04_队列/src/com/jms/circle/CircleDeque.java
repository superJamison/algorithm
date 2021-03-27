package com.jms.circle;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 17:19
 */
public class CircleDeque<T> {

    private int front; //队头
    private int size;
    private T[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 从队尾入队
     * @param element
     */
    public void enQueueRear(T element){
        //实现自动扩容
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 从队头入队
     * @param element
     */
    public void enQueueFront(T element){
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 从队尾出队
     * @return
     */
    public T deQueueRear(){
        int rearIndex = index(size - 1);
        T rear = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rear;
    }

    /**
     * 从队头出队
     * @return
     */
    public T deQueueFront(){
        T frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    /**
     * 获取队尾元素
     * @return
     */
    public T rear(){
        return elements[index(size - 1)];
    }

    /**
     * 获取队头元素
     * @return
     */
    public T front(){
        return elements[front];
    }

    public int index(int index){
        index += front;
        if (index < 0){
            return index + elements.length;
        }
//        return index % elements.length;
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
