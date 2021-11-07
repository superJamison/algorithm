package com.jms.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/28 16:24
 */
public class _869_重新排序得到2的幂2 {

    public static void main(String[] args) {
        _869_重新排序得到2的幂2 v = new _869_重新排序得到2的幂2();
        System.out.println(v.reorderedPowerOf2(6094));
        // 1024  2048 4096
    }

    Set<String> set = new HashSet<>();

    public boolean reorderedPowerOf2(int n) {
        initSet();
        return set.contains(countDigits(n));
    }

    private void initSet() {
        for (int i = 1; i <= 1e9; i <<= 1) {
            set.add(countDigits(i));
        }
    }

    private String countDigits(int n) {
        char[] chars = new char[10];
        while (n != 0){
            chars[n % 10]++;
            n /= 10;
        }
        return new String(chars);
    }

}
