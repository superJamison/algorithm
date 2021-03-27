package com.jms;

import com.jms.circle.CircleLinkedList;
import com.jms.circle.JosephCircleLinkedList;
import com.jms.circle.SingleCircleLinkedList;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/18 23:38
 */
public class Main {
    public static void main(String[] args) {
        JosephCircleLinkedList<Integer> joseph = new JosephCircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            joseph.add(i);
        }
        System.out.println(joseph);
        joseph.reset();
        while (!joseph.isEmpty()){
            joseph.next();
            joseph.next();
            System.out.println(joseph.remove());
        }
        System.out.println(joseph);
    }
        // 2 4 5 7 8
    public void test(){
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        list.add(1);
        list.add(1, 23);
        list.add(list.size(), 50);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        System.out.println(list.set(0, 10));
        System.out.println(list);
        System.out.println(list.indexOf(5));
        list.clear();
        System.out.println(list);
    }
}
