package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/26 17:38
 */
public class _剑指_Offer_19_正则表达式匹配 {

    public boolean isMatch(String s, String p) {
        int m = s.length() + 1;
        int n = p.length() + 1;

        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;

        // 初始化首行
        for(int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';

        // 状态转移
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(p.charAt(j - 1) == '*') {
                    if(dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if(dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 2.
                    else if(dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 3.
                } else {
                    if(dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if(dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public boolean isMatch1(String s, String p) {
        int m = s.length() + 1;
        int n = p.length() + 1;

        // dp[i][j] 表示第s的前i个字符与p的前j个字符是否匹配
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;

        // 初始化第1行
        for (int i = 2; i < n; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*'){
                    // *号匹配的字符个数是0个
                    if (dp[i][j - 2]) dp[i][j] = true;
                    //字符p[j - 2]多出现一次
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true;
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;
                }else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;
                    else if (dp[i -1 ][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
