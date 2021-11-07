package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/23 10:33
 */
public class _剑指_Offer_20_表示数值的字符串 {

    public static void main(String[] args) {
        _剑指_Offer_20_表示数值的字符串 v = new _剑指_Offer_20_表示数值的字符串();
        System.out.println(v.isNumber("5897972791"));
        System.out.println(Integer.MAX_VALUE);
    }

    public boolean isNumber(String s) {
        s = s.trim();

        // 整数
        boolean integerNumber = isIntegerNumber(s, 0, s.length());
        if (integerNumber) return true;

        // 数值
        int e = s.indexOf("e");
        if (e == -1) {
            e = s.indexOf("E");
        }
        if (e != -1) {
            // 判断e后面是否为整数
            boolean flag = isIntegerNumber(s, e + 1, s.length());
            if (!flag) return false;

            // 判断e前面是否是一个整数或小数
            boolean b = isIntegerNumber(s, 0, e) || isDecimalNumber(s, 0, e);
            return b;
        }

        return isDecimalNumber(s, 0, s.length());
    }

    // 判断是否是小数
    private boolean isDecimalNumber(String s, int l, int r) {
        String substring = s.substring(l, r);
        boolean b1 = substring.startsWith("-.");
        if (!b1){
            b1 = substring.startsWith("+.");
        }
        if (b1) {
            String substring1 = substring.substring(l + 2, r);
            boolean b =substring1.startsWith("+") || substring1.startsWith("-");
            if (b) return false;
            return isIntegerNumber(s, l + 2, r);
        }

        int i = substring.indexOf('.');
        if (i == 0) {
            boolean flag = isAddOrSub(s, i + 1, r);
            if (flag) return false;
            return isIntegerNumber(s, i + 1, r);
        }
        if (i == r - 1) {
            return isIntegerNumber(s, 0, i);
        }

        boolean flag = isAddOrSub(s, i + 1, r);
        if (flag) return false;
        boolean b = isIntegerNumber(s, i + 1, r);

        boolean c = isIntegerNumber(s, 0, i);

        return c && b;
    }

    // 是否是+、-开头
    private boolean isAddOrSub(String s, int l, int r) {
        String substring = s.substring(l, r);
        return substring.startsWith("-") || substring.startsWith("+");
    }

    // 判断是否为整数
    private boolean isIntegerNumber(String s, int l, int r) {
        try {
            Long.valueOf(s.substring(l, r));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
