package com.jms.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/21 17:27
 */
public class _剑指_Offer_57_II_和为s的连续正数序列 {

    public static void main(String[] args) {
        _剑指_Offer_57_II_和为s的连续正数序列 v = new _剑指_Offer_57_II_和为s的连续正数序列();
        int[][] continuousSequence = v.findContinuousSequence(9);

        for (int[] ints : continuousSequence) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();

        int mid = target >> 1;
        if ((target & 1) == 1) {
            result.add(new int[]{mid, mid + 1});
        }
        int sum = 0, l = mid, r = l;
        while (l > 0) {
            sum += l;
            if (sum < target) {
                l--;
            } else if (sum == target) {
                int[] ints = new int[r - l + 1];
                for (int i = l; i <= r; i++) {
                    ints[i - l] = i;
                }
                result.add(ints);
                sum -= r;
                r--;
                l--;
            } else {
                sum -= r;
                r--;
                l--;
            }
        }

        Collections.reverse(result);
        return result.size() == 0 ? new int[][]{} : result.toArray(new int[][]{new int[result.size()]});
    }
}
