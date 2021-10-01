package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/12 22:47
 */
public class _39_组合总和 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();



        return result;
    }
}
