package com.jms.数组;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/5 22:35
 */
public class _284_顶端迭代器 {

}

class PeekingIterator implements Iterator<Integer> {
    List<Integer> nums;
    int index;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        index = 0;
        nums = new ArrayList<>();
        while (iterator.hasNext()){
            nums.add(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nums.get(index);
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return nums.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < nums.size();
    }
}
