package com.jms.队列;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/24 11:04
 */
public class _剑指_Offer_59_II_队列的最大值 {

    public static void main(String[] args) {

    }

}

class MaxQueue {
    List<Integer> list;
    int max = -1;

    public MaxQueue() {
        list = new LinkedList<>();
    }

    public int max_value() {
        return max;
    }

    public void push_back(int value) {
        list.add(value);
        if (value > max){
            max = value;
        }
    }

    public int pop_front() {
        if (list.size() == 0) return -1;
        Integer remove = list.remove(0);
        if (remove == max){
            if (list.size() == 0) {
                max = -1;
            }else {
                max = list.get(0);
                for (int i = 1; i < list.size(); i++) {
                    Integer cur = list.get(i);
                    max = cur > max ? cur : max;
                }
            }
        }
        return remove;
    }
}
