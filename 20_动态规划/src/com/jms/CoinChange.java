package com.jms;

/**
 * 动态规划的一般步骤：
 *  1.定义状态（状态是原问题，子问题的解）
 *      比如定义dp[i]的含义
 *  2.设置初始状态（边界）
 *      比如dp[0]的值
 *  3.确定状态转移方程
 *      比如dp[i]和dp[i - 1]的关系
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/21 23:19
 */
public class CoinChange {

    public static void main(String[] args) {
        coins4(41, new int[]{1, 5, 20, 25});
    }

    /**
     * 递推 自底向上
     * @param f 所有的面值
     */
    private static int coins4(int n, int[] f){
        if (n < 1 || f == null || f.length == 0) return -1;
        int[] dp = new int[n + 1];
        int[] faces = new int[dp.length];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < f.length; j++) {
                if (i >= f[j] && dp[i - f[j]] < min) {
                    min = dp[i - f[j]];
                    faces[i] = f[j];
                }
            }
            dp[i] = min + 1;
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("【" + i + "】");
            print(faces, i, dp[i]);
            System.out.println("凑够" + i + "分钱，最少需要" + dp[i] + "枚硬币！");
        }

        return dp[n];
    }

    private static void print(int[] faces, int n, int count) {
//        System.out.println(faces[n]);
//        System.out.println(faces[n - faces[n]]);
//        System.out.println(faces[n - faces[n - faces[n]]  - faces[n]]);
        int k = n;
        for (int i = 0; i < count; i++) {
            System.out.print(faces[k] + "      ");
            k -= faces[k];
        }
        System.out.println();
    }

    /**
     * 递推 自底向上
     * @param n
     * @return
     */
    private static int coins(int n){
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1];
            if (i >= 5) min = Math.min(dp[i - 5], min);
            if (i >= 20) min = Math.min(dp[i - 20], min);
            if (i >= 25) min = Math.min(dp[i - 25], min);
            dp[i] = min + 1;
        }
        return dp[n];
    }

    /**
     * 记忆化搜索（自顶向下）
     */
    private static int coins2(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        int[] faces = new int[]{1, 5, 20, 25};
        for (int face : faces) {
            if (n < face) break;
            dp[face] = 1;
        }
        return coins2(n, dp);
    }

    private static int coins2(int n, int[] dp) {
        if (n < 1) return Integer.MAX_VALUE;
        if (dp[n] == 0){
            int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
            int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 暴力递归（自顶向下，有重复计算）
     */
    private static int coins1(int n) {
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;
        int min1 = Math.min(coins1(n - 25), coins1(n - 20));
        int min2 = Math.min(coins1(n - 5), coins1(n - 1));
        return Math.min(min1, min2) + 1;
    }
}
