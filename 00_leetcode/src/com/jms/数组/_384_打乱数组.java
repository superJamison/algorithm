package com.jms.数组;

import java.util.Random;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/3 15:36
 */
public class _384_打乱数组 {

}

class Solution {
    int[] result;
    int[] origin;
    Random random = new Random();

    public Solution(int[] nums) {
        this.result = nums;
        this.origin = nums.clone();
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        for (int i = 0; i < result.length; i++) {
            swap(i, random.nextInt(result.length - i) + i);
        }
        return result;
    }

    private void swap(int i, int j){
        int temp = result[i];
        result[i] = result[j];
        result[j] = temp;
    }

}
