package com.jms.数组;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/swap-numbers-lcci/
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/5/30 16:28
 */
public class _16_01_交换数字 {
    public static void main(String[] args) {
        Arrays.stream(swapNumbers(new int[]{1, 2})).forEach(System.out::println);
    }

    /**
     * [1, 2] --> [2, 1]
     *
     * @param numbers
     * @return
     */
    public static int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] + numbers[1];
        numbers[1] = numbers[0] - numbers[1];
        numbers[0] = numbers[0] - numbers[1];
        return numbers;
    }
}
