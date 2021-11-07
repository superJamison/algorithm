package com.jms.模拟;

import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/22 11:37
 */
public class _剑指_Offer_31_栈的压入弹出序列 {

    public static void main(String[] args) {
        _剑指_Offer_31_栈的压入弹出序列 v = new _剑指_Offer_31_栈的压入弹出序列();
        System.out.println(v.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{1,3,2,5,4}));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) return true;

        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < popped.length; i++) {
            int cur = popped[i];
            if (!stack.isEmpty() && stack.peek() == cur){
                stack.pop();
                continue;
            }
            while (index < pushed.length && pushed[index] != cur){
                stack.push(pushed[index++]);
            }
            index++;
        }

        return index == popped.length;
    }
}
