package com.jms.动态规划;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/26 16:57
 */
public class _剑指_Offer_49_丑数 {

    public static void main(String[] args) {
        // Java已经不推荐这种方式了
        Stack<Integer> stack1 = new Stack<>();
        // 推荐使用
        ArrayDeque<Integer> stack2 = new ArrayDeque<>();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(3, 3);
        map.put(1, 1);
        map.put(2, 2);

        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });

        System.out.println("==================================");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // 动态规划
    public int nthUglyNumber(int n) {
        // dp[i]表示第i+1个丑数
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;

        for (int i = 1; i < n; i++) {
            int n1 = dp[a] * 2, n2 = dp[b] * 3, n3 = dp[c] * 5;
            dp[i] = Math.min(n1, Math.min(n2, n3));
            if (dp[i] == n1) a++;
            if (dp[i] == n2) b++;
            if (dp[i] == n3) c++;
        }

        return dp[n - 1];
    }

    public int nthUglyNumber1(int n) {
        int[] dp = new int[n];
        int a = 0, b = 0, c = 0;
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(n2, Math.min(n3, n5));
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }

        return dp[n - 1];
    }
}
