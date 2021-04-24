package com.jms;

/**
 * 汉诺泰问题
 * 思路：假设a塔，中有n个盘子
 *  有两种情况：
 *   a)、只有一个盘子的情况，就直接将盘子从a塔移动到c塔上
 *   b)、有n个盘子，那么就要分三步
 *      1、将n-1个盘子先从a移动到b（这里又是一个子问题，递归）
 *      2、再将最底下的盘子从a移动到c
 *      3、最后再在将b中的n-1个盘子移动到c上
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/18 21:40
 */
public class Hanoi {

    public static void main(String[] args) {
        hanoi(4, "A", "B", "C");
    }

    private static void hanoi(int n, String p1, String p2, String p3){
        if (n == 1){
            move(n, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);
    }

    private static void move(int n, String from, String to){
        System.out.println(n + "号盘子，从" + from + "移动到" + to);
    }

}
