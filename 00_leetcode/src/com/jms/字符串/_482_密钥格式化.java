package com.jms.字符串;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/4 21:00
 */
public class _482_密钥格式化 {
    public static void main(String[] args) {
        _482_密钥格式化 v = new _482_密钥格式化();

        System.out.println(v.licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(v.licenseKeyFormatting("2-5g-3-J", 2));
        System.out.println(v.licenseKeyFormatting("--a-a-a-a--", 2));
    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder();

        int index = s.length() - 1;

        while (index >= 0){
            for (int i = k; i > 0; i--) {
                if (index < 0) break;
                char charAt = s.charAt(index);
                if (charAt != '-'){
                    if (Character.isDigit(charAt)){
                        stringBuilder.append(charAt);
                    }else {
                        stringBuilder.append(Character.toUpperCase(charAt));
                    }
                }else {
                    i++;
                }
                index--;
            }
            stringBuilder.append("-");
        }

        while (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == '-'){
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }


        return stringBuilder.reverse().toString();
    }
}
