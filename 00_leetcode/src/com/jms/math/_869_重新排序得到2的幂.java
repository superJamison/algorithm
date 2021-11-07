package com.jms.math;

import sun.plugin.WJcovUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/28 16:24
 */
public class _869_重新排序得到2的幂 {

    public static void main(String[] args) {
        _869_重新排序得到2的幂 v = new _869_重新排序得到2的幂();
        System.out.println(v.reorderedPowerOf2(6094));
        // 1024  2048 4096
    }

    public boolean reorderedPowerOf2(int n) {
        if (numIsPowerOf2(n)) return true;

        // 排列之后再判断数字是否是2的次幂
        List<Integer> list = new ArrayList<>();
        int cur = n;
        while (cur != 0){
            list.add(cur % 10);
            cur /= 10;
        }

        return dfs(list, 0, 0, true, list.size());
    }

    private boolean dfs(List<Integer> list, int i, int num, boolean isZero, int n) {
        if (i == n){
            return numIsPowerOf2(num);
        }

        boolean oldIsZero = isZero;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) == 0 && isZero) continue;
            isZero = false;
            Integer cur = list.remove(j);
            if (dfs(list,i + 1, num * 10 + cur, isZero, n)){
                return true;
            }
            list.add(j, cur);
            isZero = oldIsZero;
        }
        return false;
    }

    private boolean numIsPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }
}
