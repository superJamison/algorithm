package com.jms.数组;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/14 22:05
 */
public class _1_两数之和 {

    public static void main(String[] args) {
        int[] nums =  new int[]{2,7,11,15};
        int[] ints = twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)){
                return new int[]{map.get(sub), i};
            }else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("找不到结果！");
    }

//    public static int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return null;
//    }


}
