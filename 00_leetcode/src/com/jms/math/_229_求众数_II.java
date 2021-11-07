package com.jms.math;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/22 12:34
 */
public class _229_求众数_II {

    public List<Integer> majorityElement(int[] nums) {
        int count = nums.length / 3;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) continue;
            int orDefault = map.getOrDefault(num, 0) + 1;
            if (orDefault > count) set.add(num);
            map.put(num, orDefault);
        }

        return new ArrayList<>(set);
    }
}
