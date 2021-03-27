package com.jms.队列;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 12:52
 */
public class _232_用栈实现队列 {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public _232_用栈实现队列() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** 入队 */
    public void push(int x) {
        inStack.push(x);
    }

    /** 出队 */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** 获取队头元素 */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /** 判断队列是否为空 */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack(){
        java.util.Queue s;
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }
}
