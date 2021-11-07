package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/2 23:11
 */
public class _405_数字转换为十六进制数 {

    public static void main(String[] args) {
        _405_数字转换为十六进制数 v = new _405_数字转换为十六进制数();

        String s = v.toHex(26);
        System.out.println(s);

        System.out.println(Integer.toHexString(99));
        System.out.println(-2 << 3);
    }

    public String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int u = num & 15;
            char c = (char)(u + '0');
            if (u >= 10) c = (char)(u - 10 + 'a');
            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }

    public String toHex1(int num) {
        if (num == 0) return "0";
        long _num = num;
        StringBuilder sb = new StringBuilder();
        if(_num < 0) _num = (long)(Math.pow(2, 32) + _num);
        while (_num != 0) {
            long u = _num % 16;
            char c = (char)(u + '0');
            if (u >= 10) c = (char)(u - 10 + 'a');
            sb.append(c);
            _num /= 16;
        }
        return sb.reverse().toString();
    }
}
