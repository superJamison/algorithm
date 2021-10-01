package com.jms.字符串;

import java.util.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/28 20:24
 */
public class _6_Z_字形变换 {
    public static void main(String[] args) {
        String paypalishiring = convert("PAYPALISHIRING", 3);

        System.out.println(paypalishiring);

        // PAHNAPLSIIGYIR
        // PAHNAPLSIIGYIR
        // PAHNAPLSIIGYIR
    }

    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return s;
        if (numRows == 1) return s;

        List<StringBuilder> list = new ArrayList<>();

        numRows--;
        for (int i = 0; i <= numRows; i++) {
            list.add(new StringBuilder());
        }

        int row = 0;
        boolean flag = true;
        for (char c : s.toCharArray()) {
            list.get(row).append(c);

            if (row == 0){
                flag = true;
            }else if (row == numRows){
                flag = false;
            }

            if (flag){
                row++;
            }else {
                row--;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= numRows; i++) {
            stringBuilder.append(list.get(i).toString());
        }

        return stringBuilder.toString();
    }

    public static String convert1(String s, int numRows) {
        if (s == null || s.length() == 0) return s;
        if (numRows == 1) return s;

        Map<Integer, StringBuilder> map = new HashMap<>();

        for (int i = 0; i < numRows; i++) {
            map.put(i, new StringBuilder());
        }

        int index = 0, row = 0;
        boolean flag = true;
        while (index < s.length()){
            map.get(row).append(s.charAt(index++));

            if (row == 0){
                flag = true;
            }else if (row == numRows - 1){
                flag = false;
            }

            if (flag){
                row++;
            }else {
                row--;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            stringBuilder.append(map.get(i).toString());
        }

        return stringBuilder.toString();
    }
}
