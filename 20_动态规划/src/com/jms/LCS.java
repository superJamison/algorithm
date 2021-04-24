package com.jms;

/**
 * 最长公共子序列
 * 求nums1数组和nums2数组的最长公共子序列的长度
 *      1.定义规则
 *          dp（i, j）代表nums1的前i个元素和nums2的前j个元素的最长公共子序列的长度
 *      2.边界
 *          dp（0, j） 和 dp（i, 0） 的初始值为0
 *      3.dp（i, j）和dp（i - 1, j - 1）的关系
 *          if（nums[i - 1] == nums[j - 1]）
 *              dp(i, j) = dp(i - 1, j - 1) + 1
 *          else
 *              dp(i, j) = Math(dp(i - 1, j), dp(i - 1, j))
 * @author Jamison
 * @version 1.0
 * @date 2021/4/23 23:55
 */
public class LCS {
    public static void main(String[] args) {
        System.out.println(lcs(new int[]{1, 4, 9, 10}, new int[]{1, 3, 5, 9, 10}));
    }



    /**
     * 动态规划实现  时间复杂度O(n*m) 一个一维数组进一步优化空间复杂度
     */
    private static int lcs(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        int[] rowNums = nums1, colNums = nums2;
        if (nums1.length < nums2.length){
            rowNums = nums2;
            colNums = nums1;
        }
        int[] dp = new int[colNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length; j++) {
                int leftTop = cur;
                //保存之前的dp[j]，留作下一轮循环作为leftTop
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]){
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colNums.length];
    }

    /**
     * 动态规划实现  时间复杂度O(n*m) 一个一维数组进一步优化空间复杂度
     */
    private static int lcs4(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = cur;
                //保存之前的dp[j]，留作下一轮循环作为leftTop
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]){
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[nums2.length];
    }

    /**
     * 动态规划实现  时间复杂度O(n*m) 使用滚动数组进一步优化空间复杂度
     */
    private static int lcs3(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                int row = i & 1;
                int prevRow = (i - 1) & 1;
                if (nums1[i - 1] == nums2[j - 1]){
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                }else {
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
                }
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }

    /**
     * 动态规划实现  时间复杂度O(n*m)
     */
    private static int lcs2(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 递归实现 时间复杂度O(2^n)
     */
    private static int lcs1(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        return lcs1(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     * nums1数组和nums2数组的最长公共子序列的长度
     */
    private static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]){
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcs1(nums1, i - 1, nums2, j),
                lcs1(nums1, i, nums2, j - 1));
    }
}
