package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/30 21:49
 */
public class _剑指_Offer_05_替换空格 {

    public static void main(String[] args) {
        System.out.println(replaceSpace("   "));
    }

    public static String replaceSpace(String s) {
        String[] s1 = s.split(" ");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s1.length; i++) {
            if (i == s1.length - 1){
                stringBuilder.append(s1[i]);
            }else {
                stringBuilder.append(s1[i]);
                stringBuilder.append("%20");
            }
        }

        return stringBuilder.toString();
    }

    public String replaceSpace1(String s) {
        return s.replace(" ", "%20");
    }
}
