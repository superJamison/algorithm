package com.jms;

import com.jms.list.ArrayList;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/22 21:54
 */
public class Stack<T>{

    private ArrayList<T> list = new ArrayList<>();

    public void clear(){
        list.clear();
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push(T element){
        list.add(element);
    }

    public T pop(){
        return list.remove(list.size() - 1);
    }

    public T top(){
        return list.get(list.size() - 1);
    }
}
