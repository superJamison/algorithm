package com.jms.数组;

/**
 * https://leetcode-cn.com/problems/guess-numbers/
 * @author Jamison
 * @version 1.0
 * @date 2021/5/16 22:46
 */
public class _01_猜数字 {

    public static int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(game(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
    }
}
