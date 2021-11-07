package com.jms.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/3 22:07
 */
public class _166_分数到小数 {

    public static void main(String[] args) {
        _166_分数到小数 v = new _166_分数到小数();

        System.out.println(v.fractionToDecimal(-2147483648, 1));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(2147483647 / 37);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(-2147483648);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long _numerator = numerator, _denominator = denominator;

        if (_numerator == 0) return "0";

        StringBuilder builder = new StringBuilder();

        if ((_numerator < 0 && _denominator > 0) || (_numerator > 0 && _denominator < 0)) {
            builder.append("-");
        }

        _numerator = Math.abs(_numerator);
        _denominator = Math.abs(_denominator);

        builder.append(_numerator / _denominator);
        int index = builder.length();

        // 计算小数部分
        long remainder = _numerator % _denominator;
        // Map<前面计算过的余数，这个余数的索引>
        Map<Long, Integer> map = new HashMap<>();

        if (remainder != 0) {
            builder.append(".");
            index++;
        }

        while (remainder != 0 && !map.containsKey(remainder)) {
            long i = remainder * 10;
            builder.append(Math.abs(i / _denominator));
            map.put(remainder, index++);
            remainder = i % _denominator;
        }

        if (remainder != 0) {
            builder.insert(map.get(remainder), "(");
            builder.append(")");
        }

        return builder.toString();
    }
}
