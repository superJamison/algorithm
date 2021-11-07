package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/29 15:21
 */
public class _413_等差数列划分 {

    public static void main(String[] args) {
        _413_等差数列划分 v = new _413_等差数列划分();
        System.out.println(v.numberOfArithmeticSlices(new int[]{1, 2, 3, 8, 9, 10}));
    }

    // 动态规划
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;

        int result = 0;
        int[] dp = new int[n];
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] << 1 == nums[i] + nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                result += dp[i];
            }
        }

        return result;
    }

    // 双指针
    public int numberOfArithmeticSlices2(int[] nums) {
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int index = i + 1;
            int dValue = nums[index++] - nums[i];
            while (index < nums.length) {
                if ((nums[index] - nums[index - 1]) == dValue) index++;
                else break;
            }
            result += index - i - 2;
        }
        return result;
    }

    public int numberOfArithmeticSlices1(int[] nums) {
        if (nums.length < 3) return 0;
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            // 先向右走一步
            int r = i + 1;
            int dValue = nums[r++] - nums[i];
            while (r < nums.length) {
                if (nums[r] - nums[r++ - 1] == dValue) result++;
                else break;
            }
        }

        return result;
    }
}
