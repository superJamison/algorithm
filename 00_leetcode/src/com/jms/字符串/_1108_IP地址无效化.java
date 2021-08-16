package com.jms.字符串;

/**
 * https://leetcode-cn.com/problems/defanging-an-ip-address/
 * @author Jamison
 * @version 1.0
 * @date 2021/5/16 22:30
 */
public class _1108_IP地址无效化 {

    public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public static String defangIPaddr1(String address) {
        String[] split = address.split("[.]");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                builder.append(split[i]);
            } else {
                builder.append("[.]").append(split[i]);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(defangIPaddr1("1.1.1.1"));
    }
}
