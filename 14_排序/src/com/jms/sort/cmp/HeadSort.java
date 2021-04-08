package com.jms.sort.cmp;

import com.jms.sort.Sort;

/**
 *  堆排序   不是稳定的排序  平均复杂度：O(nlogn)  空间：O(1)
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 21:17
 */
public class HeadSort<T extends Comparable<T>> extends Sort<T> {

    private int headSize;

    @Override
    protected void sort() {
        //原地建堆
        headSize = arrays.length;
        for (int i = (headSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (headSize > 1){
            //交换堆顶元素和尾元素
            swap(0, --headSize);

            //对0位置进行siftDown,恢复堆的性质
            siftDown(0);
        }

    }

    /**
     * 让index位置的元素下虑
     * @param index
     */
    private void siftDown(int index) {
        //获取堆顶元素的值
        T element = arrays[index];
        //计算第一个叶子节点的索引（第一个叶子结点的索引==非叶子结点的数量==floor(size/2)）
        int half = headSize >> 1;
        while (index < half){
            //index节点有两种情况
            // 1.index的节点只有左节点
            // 2.index的节点同时有左右节点

            //默认拿到左节点进行比较
            int childIndex = (index << 1) + 1;
            T child = arrays[childIndex];

            //右子节点
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < headSize && cmpElement(arrays[rightChildIndex], child) > 0){
                child = arrays[rightChildIndex];
                childIndex = rightChildIndex;
            }

            if (cmpElement(element, child) >= 0){
                break;
            }

            arrays[index] = child;
            index = childIndex;
        }
        arrays[index] = element;
    }
}
