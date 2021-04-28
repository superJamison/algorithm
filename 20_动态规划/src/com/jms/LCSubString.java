package com.jms;

/**
 * 最长公共字串
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/24 18:49
 */
public class LCSubString {
    public static void main(String[] args) {
        System.out.println(lcs("ABCD", "BCD"));
    }

    private static int lcs(String str1, String str2){
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0) return 0;
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0) return 0;
        char[] rowChars = chars1, colChars = chars2;
        if (chars1.length < chars2.length){
            rowChars = chars2;
            colChars = chars1;
        }

        int[] dp = new int[colChars.length + 1];
        int max = 0;
        for (int i = 1; i <= rowChars.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colChars.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (chars1[i - 1] != chars2[j - 1]) {
                    dp[j] = 0;
                }else {
                    dp[j] = leftTop + 1;
                    max = Math.max(dp[j], max);
                }
            }
        }
        return max;
    }

    private static int lcs1(String str1, String str2){
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0) return 0;
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0) return 0;

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] != chars2[j - 1]) continue;
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
}
