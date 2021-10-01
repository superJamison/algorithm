package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/5 20:03
 */
public class _11_盛最多水的容器 {

    public static void main(String[] args) {
        int result = maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        System.out.println(result);
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;

        int left = 0, right = height.length - 1;
        int max = 0;

        while (left < right) {
            int minHeiht = Math.min(height[left], height[right]);
            max = Math.max(max, (right - left) * minHeiht);
            while (left < right && height[left] <= minHeiht) left++;
            while (left < right && height[right] <= minHeiht) right--;
        }

        return max;
    }

    public static int maxArea1(int[] height) {
        if (height == null || height.length < 2) return 0;

        int left = 0, right = height.length - 1;
        int max = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (left - 1 > 0 && height[left] < height[left - 1]){
                    left++;
                    continue;
                }
                max = Math.max(max, (right - left) * height[left++]);
            } else {
                if (right + 1 < height.length && height[right] < height[right + 1]){
                    right--;
                    continue;
                }
                max = Math.max(max, (right - left) * height[right--]);
            }
        }

        return max;
    }

    public static int maxArea2(int[] height) {
        if (height == null || height.length < 2) return 0;

        int left = 0, right = height.length - 1;
        int max = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(max, (right - left) * height[left++]);
            } else {
                max = Math.max(max, (right - left) * height[right--]);
            }
        }

        return max;
    }
}
