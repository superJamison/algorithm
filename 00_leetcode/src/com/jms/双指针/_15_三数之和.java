package com.jms.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/2 21:39
 */
public class _15_三数之和 {
    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});

        if (lists != null) {
            for (List<Integer> list : lists) {
                for (Integer integer : list) {
                    System.out.print(integer + "  ");
                }
                System.out.println();
            }
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || length < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) return result;

            // 去除重复的解
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            int left = i + 1, right = length - 1;

            while (left < right) {
                int temp = nums[left] + nums[right];

                if (temp < target) {
                    left++;
                } else if (temp > target) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[++left]) ;
                    while (left < right && nums[right] == nums[--right]) ;
                }
            }
        }

        return result;
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || length < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) return result;

            // 去除重复的解
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int current = nums[i];
            int left = i + 1, right = length - 1;

            while (left < right) {
                int temp = current + nums[left] + nums[right];

                if (temp == 0) {
//                    List<Integer> list = new ArrayList<>();
//                    list.add(current);
//                    list.add(nums[left]);
//                    list.add(nums[right]);
//                    result.add(list);

                    result.add(Arrays.asList(current, nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (temp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
