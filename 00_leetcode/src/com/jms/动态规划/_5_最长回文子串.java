package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/5/4 20:10
 */
public class _5_最长回文子串 {

    /**
     * 马拉车算法  s->oldChars[]
     * 思路：首先将oldChars的首尾都加上一个oldChars里面都不包含的字符，变成chars
     * 再在chars的基础上在chars的每个字符的中间都加上一个相同的字符
     * 此时chars就是我们要操作的字符数组
     * <p>
     * 创建一个m[chars.length]的数组，
     * m[i]表示以chars[i]为中心最大回文子串的右边或者左边的字符个数，也代表以chars[i]为中心oldChars的最大回文子串的长度。
     * <p>
     * 设定一个l，li，c，i， r
     * 已知：l，r是已经求出的以c为中心的最大回文子串的最左边和最右边的索引
     * li是以c对称的索引值
     * 求：求出m[i]的值
     * 有四种情况：
     * 1）、当i == r的时候，直接以i为中心向左右两边开始查找最大回文子串，更新c，r
     * 2）、当i + m[li] > r的时候，那么m[i]的值至少为 r - i，但是还不确定是否为这个值，需要尝试一下从r+1位置扩展查找最大回文子串，更新c，r
     * 3）、当i + m[li] == r的时候，那么m[i]的值至少为 m[li]，但是还不确定是否为这个值，需要尝试一下从r+1位置扩展查找最大回文子串，更新c，r
     * 4）、当i + m[li] < r的时候，那么m[i]的值为 m[li]
     */
    public String longestPalindromeManacher(String s) {
        if (s == null) return "";
        char[] oldChars = s.toCharArray();
        if (oldChars.length <= 1) {
            return s;
        }
        // 预处理s
        char[] chars = preProcess(oldChars);
        int[] m = new int[chars.length];
        // 初始化c，r，lastIndex，lastIndex直接从倒数第三个开始
        int c = 1, r = 1, lastIdx = m.length - 2;
        int maxLen = 0, idx = 1;
        for (int i = 2; i < lastIdx; i++) {
            if (i < r){
                int li = (c << 1) - i;
                if (i + m[li] <= r){
                    m[i] = m[li];
                }else {
                    m[i] = r - i;
                }
            }
            // 以i为中心，向左右扩展
            while (chars[i + m[i] + 1] == chars[i - m[i] - 1]){
                m[i]++;
            }
            // 更新c，r
            if (i + m[i] > r){
                c = i;
                r = i + m[i];
            }
            if (m[i] > maxLen){
                maxLen = m[i];
                idx = i;
            }
        }
        int begin = (idx - maxLen) >> 1;
        return new String(oldChars, begin, maxLen);
    }

    /**
     * 预处理char，添加上一些特殊字符
     */
    private char[] preProcess(char[] oldChars) {
        char[] chars = new char[(oldChars.length << 1) + 3];
        chars[0] = '^';
        chars[1] = '#';
        chars[chars.length - 1] = '$';
        for (int i = 0; i < oldChars.length; i++) {
            int idx = (i + 1) << 1;
            chars[idx] = oldChars[i];
            chars[idx + 1] = '#';
        }
        return chars;
    }


    /**
     * 扩展中心法优化
     */
    public String longestPalindromeEx2(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();
        if (chars.length <= 1) {
            return s;
        }
        // 最长回文子串的长度
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        int i = 0;
        while (i < chars.length) {
            int l = i - 1;
            int r = i;
            // 找到第一个不等于chars[i]的字符，作为r
            while (++r < chars.length && chars[i] == chars[r]) ;
            // 跳过和chars[i]相等的一串子串，直接从与chars[i]不相等的索引开始下一轮查找
            i = r;
            // 以l开始向左边扩展、以r开始向右边扩展查找最长回文子串
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                l--;
                r++;
            }
            // 让l指向找到的最长回文子串的开始位置
            l++;
            int len = r - l;
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 扩展中心法
     */
    public String longestPalindromeEx(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();
        if (chars.length <= 1) {
            return s;
        }
        // 最长回文子串的长度
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            // 获取以i位置字符为中心向左右扩展的最长回文子串的长度
            int len1 = palindromeLen(chars, i - 1, i + 1);
            // 获取以i右边间隙位置为中心向左右扩展的最长回文子串的长度
            int len2 = palindromeLen(chars, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin = i - ((len1 - 1) >> 1);
            }
        }
        // 以第一个间隙为中心向左右扩展的情况
        if (chars[0] == chars[1] && maxLen < 2) {
            maxLen = 2;
            begin = 0;
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * @return 返回以l位置开始字符向左边扩展，r位置开始字符向右边扩展
     */
    private int palindromeLen(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    /**
     * 动态规划法
     */
    public String longestPalindromeDp(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        // 最长回文子串的长度
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        // 保存chars[i, j] 是否为回文子串 （左闭右闭）
        boolean dp[][] = new boolean[chars.length][chars.length];
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                int len = j - i + 1;
                dp[i][j] = (chars[i] == chars[j]) && (len <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && len > maxLen) { // 说明chars[i, j]是回文子串
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
