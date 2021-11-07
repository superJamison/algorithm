package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/5 21:29
 */
public class _42_接雨水 {


    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) return 0;

        int l = 0, r = n - 1;
        int lMax = height[l], rMax = height[r];
        int result = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= lMax) lMax = height[l++];
                else result += (lMax - height[l++]);
            }else {
                if (height[r] >= rMax) rMax = height[r--];
                else result += (rMax - height[r--]);
            }
        }
        return result;
    }

    public int trap1(int[] height) {
        int n = height.length;
        if (n < 3) return 0;

        // 找到最高的那根柱子的index
        int maxHeightIndex = 0;
        for (int i = 1; i < n; i++) {
            if (height[i] > height[maxHeightIndex]) maxHeightIndex = i;
        }

        // 从左往右遍历到最高柱子计算可以接到的雨水
        int result = 0;
        int l = height[0];
        for (int i = 0; i < maxHeightIndex; i++) {
            if (height[i] < l) {
                result += l - height[i];
            } else {
                l = height[i];
            }
        }

        // 从右往左遍历到最高柱子计算可以接到的雨水
        int r = height[n - 1];
        for (int i = n - 2; i > maxHeightIndex; i--) {
            if (height[i] < r) {
                result += r - height[i];
            } else {
                r = height[i];
            }
        }

        return result;
    }
}
