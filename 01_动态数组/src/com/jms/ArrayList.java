package com.jms;

/**
 * 手动实现一个可扩容的数组
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/3/17 21:32
 */
public class ArrayList<T> {

    /**
     * 元素的数量
     */
    private int size = 0;

    /**
     * 所有的元素
     */
    private T[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        elements = (T[]) new Object[capacity];
    }

    /**
     * 清除所有的元素
     */
    public void clear() {
        if (size > 100) {
            elements = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }
            size = 0;
        }
        /*for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;*/
    }

    /**
     * 获取元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含指定元素
     *
     * @param element
     * @return
     */
    public boolean contains(T element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素的数组的后面
     *
     * @param element
     */
    public void add(T element) {
        add(size, element);
    }

    /**
     * 获取index对应位置的元素
     *
     * @param index 索引
     * @return
     */
    public T get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 在指定index位置设置元素，并且返回原先的元素值
     *
     * @param index   索引
     * @param element 要插入的元素
     * @return 返回原先的元素值
     */
    public T set(int index, T element) {
        rangeCheck(index);
        T old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 往index索引位置上添加元素
     *
     * @param index
     * @param element
     */
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        for (int i = size; i > index + 1; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("您要设置的位置超出了范围！Index:" + index + ", Size:" + size);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("您要设置的位置超出了范围！Index:" + index + ", Size:" + size);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity > elements.length) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            T[] newArr = (T[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = elements[i];
            }
            elements = newArr;
            System.out.println("oldCapacity:" + oldCapacity + ",newCapacity:" + newCapacity);
        }
    }

    /**
     * 删除index位置的元素，并且返回删除的这个元素
     *
     * @param index 位置索引
     * @return 返回删除的那个元素
     */
    public T remove(int index) {
        rangeCheck(index);
        T oldEle = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
//        elements[size - 1] = null;
//        size--;
        elements[--size] = null;
        return oldEle;
    }

    /**
     * 获取指定元素的索引值（位置）
     *
     * @param element 指定的元素
     * @return 返回索引
     */
    public int indexOf(T element) {
        if (element == null){
            for (int i = 0; i < size; i++) {
            if (elements[i] == element) return i;
        }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        //size=3, [12, 13, 14]
        StringBuilder builder = new StringBuilder();
        builder.append("ArrayList{size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                builder.append(elements[i]);
            } else {
                builder.append(", ").append(elements[i]);
            }
        }
        builder.append("]}");
        return builder.toString();
    }
}
