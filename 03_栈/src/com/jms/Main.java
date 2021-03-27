package com.jms;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/22 21:54
 */
public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        stack.push(55);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        System.out.println(stack);
    }
}
