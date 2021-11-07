package com.jms.数组;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/1 14:48
 */
public class _575_分糖果 {

    public static void main(String[] args) {
        _575_分糖果 v = new _575_分糖果();
        System.out.println(v.distributeCandies(new int[]{1, 1, 2, 2, 3, 3}));
    }

    public int distributeCandies(int[] candyType){
        int count = 0;
        boolean[] flags = new boolean[200001];

        for (int i = 0; i < candyType.length; i++) {
            if (!flags[candyType[i] + 100000]){
                count++;
                flags[candyType[i] + 100000] = true;
                if (count >= (candyType.length >> 1)){
                    break;
                }
            }
        }
        return count;
    }

    public int distributeCandies2(int[] candyType){
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        return Math.min(candyType.length >> 1, set.size());
    }

    public int distributeCandies1(int[] candyType) {
        // 先分给弟弟
        int mid = candyType.length >> 1;
        Arrays.sort(candyType);

        int prev = candyType[0], type = 1;
        for (int i = 1; i < candyType.length; i++) {
            if (candyType[i] == prev) {
                if (mid > 0) mid--;
            } else {
                prev = candyType[i];
                type++;
            }
        }

        return type - mid;
    }
}
