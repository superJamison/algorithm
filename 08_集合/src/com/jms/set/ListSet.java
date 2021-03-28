package com.jms.set;

import com.jms.list.LinkedList;

import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/28 16:42
 */
public class ListSet<T> implements Set<T> {

    LinkedList<T> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(T element) {
        return list.contains(element);
    }

    @Override
    public void add(T element) {
        int index = list.indexOf(element);
        if (index != LinkedList.ELEMENT_NOT_FOUND){
            list.set(index, element);
        }else {
            list.add(element);
        }
    }

    @Override
    public void remove(T element) {
        int index = list.indexOf(element);
        if (index != LinkedList.ELEMENT_NOT_FOUND){
            list.remove(element);
        }
    }

    @Override
    public void traversal(Visitor<T> visitor) {
        if (visitor == null){
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (visitor.visit(list.get(i))) {
                return;
            }
        }
    }
}
