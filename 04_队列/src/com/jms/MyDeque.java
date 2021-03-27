package com.jms;

import com.jms.list.LinkedList;

/**
 * 双端队列
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 13:06
 */
public class MyDeque<T> {

    private LinkedList<T> linkedList = new LinkedList<>();

    public void clear(){
        linkedList.clear();
    }

    public int size(){
        return linkedList.size();
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    /**
     * 从队尾入队
     * @param element
     */
    public void enQueueRear(T element){
        linkedList.add(element);
    }

    /**
     * 从队头入队
     * @param element
     */
    public void enQueueFront(T element){
        linkedList.add(0, element);
    }

    /**
     * 从队尾出队
     * @return
     */
    public T deQueueRear(){
        return linkedList.remove(linkedList.size() - 1);
    }

    /**
     * 从队头出队
     * @return
     */
    public T deQueueFront(){
        return linkedList.remove(0);
    }

    /**
     * 获取队尾元素
     * @return
     */
    public T rear(){
        return linkedList.get(linkedList.size() - 1);
    }

    /**
     * 获取队头元素
     * @return
     */
    public T front(){
        return linkedList.get(0);
    }

    @Override
    public String toString() {
        return "Deque{" +
                "linkedList=" + linkedList +
                '}';
    }
}
