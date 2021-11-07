package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/15 18:41
 */
public class _38_外观数列 {

    public static void main(String[] args) {
        _38_外观数列 v = new _38_外观数列();
        String s = v.countAndSay(5);
        System.out.println(s);
    }

    public String countAndSay(int n) {
        if (n == 1) return "1";

        String s = countAndSay(n - 1);

        // typeCount个type
        StringBuilder stringBuilder = new StringBuilder();

        int typeCount = 1;
        char type = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (type == charAt){
                typeCount++;
            }else {
                stringBuilder.append(typeCount + String.valueOf(type));
                type = charAt;
                typeCount = 1;
            }
        }
        stringBuilder.append(typeCount + String.valueOf(type));

        return stringBuilder.toString();
    }

}
