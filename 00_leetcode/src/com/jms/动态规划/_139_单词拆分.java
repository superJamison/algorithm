package com.jms.动态规划;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/29 17:04
 */
public class _139_单词拆分 {

    public static void main(String[] args) {
        _139_单词拆分 v = new _139_单词拆分();
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("aaa");
        System.out.println(v.wordBreak("aaaaaaa", list));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);

        // dp[i]表示s的前i个字符串是否可以被切分
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        // dp[i]表示s中前i个字符串是否可拆分
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int end = 1; end <= n; end++) {
            for (int begin = 0; begin < end; begin++) {
                if (dp[begin] && set.contains(s.substring(begin, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
