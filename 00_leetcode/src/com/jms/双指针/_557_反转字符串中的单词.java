package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/19 21:00
 */
public class _557_反转字符串中的单词 {

    public static void main(String[] args) {
        System.out.println(reverseWords("My name is Jamison"));
    }

    public static String reverseWords(String s) {
        String[] sArr = s.split(" ");

        for (int i = 0; i < sArr.length; i++) {
            char[] chars = sArr[i].toCharArray();
            int left = 0, right = chars.length - 1;

            while (left < right){
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
            sArr[i] = new String(chars);
        }


        StringBuilder sb = new StringBuilder();
        if (sArr != null && sArr.length > 0) {
            for (int i = 0; i < sArr.length; i++) {
                if (i < sArr.length - 1) {
                    sb.append(sArr[i] + " ");
                } else {
                    sb.append(sArr[i]);
                }
            }
        }
        String lytype1 = sb.toString();

        return sb.toString();
    }

}
