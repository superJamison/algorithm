package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/29 15:45
 */
public class _91_解码方法 {

    public static void main(String[] args) {
        _91_解码方法 v = new _91_解码方法();
        System.out.println(v.numDecodings("2611055971756562"));
    }

    public int numDecodings(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;

        int result = 0;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            if (chars[i] == '0'){
                if (chars[i - 1] > '2' || chars[i - 1] == '0'){
                    return 0;
                }
                dp[i] = (i > 1 ? dp[i - 2] : 1);
            }else if ((chars[i - 1] == '2' && chars[i] > '6') || chars[i - 1] > '2' || chars[i - 1] == '0'){
                dp[i] = dp[i - 1];
            }else {
                dp[i] = dp[i - 1] + (i > 1 ? dp[i - 2] : 1);
            }
        }

        return dp[n - 1];
    }
}
