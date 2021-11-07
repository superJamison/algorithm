package com.jms.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/7 10:47
 */
public class _剑指_Offer_46_把数字翻译成字符串 {

    public static void main(String[] args) {
        _剑指_Offer_46_把数字翻译成字符串 v = new _剑指_Offer_46_把数字翻译成字符串();
        System.out.println(v.translateNum(12258));
    }

    public int translateNum(int num) {
        if (num == 0) return 1;

        List<Integer> list = new ArrayList<>();

        int temp = num;
        while (temp != 0){
            list.add(temp % 10);
            temp /= 10;
        }

        int[] dp = new int[list.size() + 1];
        dp[0] = 1;
        dp[1] = 1;
        // 12
        // 1 2 第一种
        // 12  第二种

        // 12258   85221
        for (int i = 1; i < list.size(); i++) {
            int n = list.get(i) * 10 + list.get(i - 1);
            if (n > 9 && n < 26){
                dp[i + 1] = dp[i] + dp[i - 1];
            }else {
                dp[i + 1] = dp[i];
            }
        }

        return dp[list.size()];
    }
}
