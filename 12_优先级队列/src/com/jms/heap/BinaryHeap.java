package com.jms.heap;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/31 21:08
 */
public class BinaryHeap<T> extends AbstractHeap<T>{

    private T[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(T[] elements, Comparator<T> comparator){
        super(comparator);
        if (elements == null || elements.length == 0){
            this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        }else {
            this.size = elements.length;
            int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
            this.elements = (T[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    private void heapify() {
        //自上而下的上滤
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }
        //自下而上的下虑
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public BinaryHeap(T[] elements){
        this(elements, null);
    }

    public BinaryHeap(Comparator<T> comparator){
        this(null, comparator);
    }

    public BinaryHeap(){
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public T get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public void add(T element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public T remove() {
        emptyCheck();

        int lastIndex = --size;
        T root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        siftDown(0);
        return root;
    }

    @Override
    public T replace(T element) {
        elementNotNullCheck(element);

        T root = null;
        if (size == 0){
            elements[0] = element;
            size++;
        }else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }


    private void emptyCheck(){
        if (size == 0){
            throw new IndexOutOfBoundsException("Heap is empty!");
        }
    }

    private void elementNotNullCheck(T element){
        if (element == null){
            throw new IllegalArgumentException("Element must not be null!");
        }
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity){
            return;
        }
        //新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * 让index位置上的元素上虑
     * @param index
     */
    private void siftUp(int index){
        T e = elements[index];
        while (index > 0){
            int pIndex = (index - 1) >> 1;
            T p = elements[pIndex];
            if (compare(e, p) <= 0){
                break;
            }

            //交换index和平Index位置的内容
            elements[index] = p;
            //重新赋值index
            index = pIndex;
        }
        elements[index] = e;
    }

    /**
     * 让index位置的元素下虑
     * @param index
     */
    private void siftDown(int index) {
        //获取堆顶元素的值
        T element = elements[index];
        //计算第一个叶子节点的索引（第一个叶子结点的索引==非叶子结点的数量==floor(size/2)）
        int half = size >> 1;
        while (index < half){
            //index节点有两种情况
            // 1.index的节点只有左节点
            // 2.index的节点同时有左右节点

            //默认拿到左节点进行比较
            int childIndex = (index << 1) + 1;
            T child = elements[childIndex];

            //右子节点
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < size && compare(elements[rightChildIndex], child) > 0){
                child = elements[rightChildIndex];
                childIndex = rightChildIndex;
            }

            if (compare(element, child) >= 0){
                break;
            }

            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

}
