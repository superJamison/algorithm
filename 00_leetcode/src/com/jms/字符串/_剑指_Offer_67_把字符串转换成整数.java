package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/23 11:58
 */
public class _剑指_Offer_67_把字符串转换成整数 {

    public static void main(String[] args) {
        _剑指_Offer_67_把字符串转换成整数 v = new _剑指_Offer_67_把字符串转换成整数();
        System.out.println(v.strToInt("42"));
    }

    public int strToInt(String str) {
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 0, sign = 1, length = str.length();
        if(length == 0) return 0;
        while(str.charAt(i) == ' ')
            if(++i == length) return 0;
        if(str.charAt(i) == '-') sign = -1;
        if(str.charAt(i) == '-' || str.charAt(i) == '+') i++;
        for(int j = i; j < length; j++) {
            if(str.charAt(j) < '0' || str.charAt(j) > '9') break;
            if(res > bndry || res == bndry && str.charAt(j) > '7')
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }

    public int strToInt1(String str) {
        str = str.trim();
        if ("".equals(str)) return 0;

        char charAt = str.charAt(0);
        long result = 0;
        if (charAt == '-') {
            int index = 1;
            while (index < str.length() && (charAt = str.charAt(index++)) >= 48 && charAt <= 57) {
                if (result > 2147483648L) return -2147483648;
                result = result * 10 + (charAt - '0');
            }
            if (result > 2147483648L) {
                return -2147483648;
            }
            return (int) -result;
        } else if (charAt == '+') {
            int index = 1;
            while (index < str.length() && (charAt = str.charAt(index++)) >= 48 && charAt <= 57) {
                if (result > 2147483647) return 2147483647;
                result = result * 10 + Integer.parseInt(String.valueOf(charAt));
            }
            if (result > 2147483647) {
                return 2147483647;
            }
            return (int) result;
        }else {
            int index = 0;
            while (index < str.length() && (charAt = str.charAt(index++)) >= 48 && charAt <= 57) {
                if (result > 2147483647) return 2147483647;
                result = result * 10 + Integer.parseInt(String.valueOf(charAt));
            }
            if (result > 2147483647) {
                return 2147483647;
            }
            return (int) result;
        }

    }
}
