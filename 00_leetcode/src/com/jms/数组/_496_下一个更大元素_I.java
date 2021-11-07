package com.jms.数组;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/26 0:13
 */
public class _496_下一个更大元素_I {

    public static void main(String[] args) {
        _496_下一个更大元素_I v = new _496_下一个更大元素_I();
        int[] ints = v.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        for (int anInt : ints) {
            System.out.print(anInt + "   ");
        }
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num){
                map.put(stack.pop(),num);
            }
            stack.push(num);
        }
        while (!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }

        return nums1;
    }
}
