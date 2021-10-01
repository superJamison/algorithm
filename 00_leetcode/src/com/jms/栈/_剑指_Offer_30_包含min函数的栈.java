package com.jms.栈;

import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/29 20:30
 */
public class _剑指_Offer_30_包含min函数的栈 {

    public static void main(String[] args) {

    }
}

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> stackMin;

    public MinStack() {
        stack = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (stackMin.isEmpty() || x <= stackMin.peek()){
            stackMin.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(stackMin.peek())){
            stackMin.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stackMin.peek();
    }
}
