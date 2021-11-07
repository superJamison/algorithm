package com.jms.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/8 23:39
 */
public class _134_加油站 {

    public static void main(String[] args) {
        _134_加油站 v = new _134_加油站();
        v.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        if (gas == null || n == 0) return -1;

        int hasGas = 0;

        // 从第i个加油站出发
        for (int i = 0; i < n; i++) {
            int j;
            for (j = i; j < cost.length + n; j++) {
                int index = j - n < 0 ? j : j - n;
                hasGas += gas[index];
                if (hasGas < cost[index]) break;
                hasGas -= cost[index];
            }
            if (j == cost.length + n) return i;
            hasGas = 0;
        }

        return -1;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        if (gas == null || n == 0) return -1;

        int hasGas = 0;

        // 从第i个加油站出发
        for (int i = 0; i < n; i++) {
            int j;
            for (j = i; j < cost.length + n; j++) {
                hasGas += gas[j % n];
                if (hasGas < cost[j % n]) break;
                hasGas -= cost[j % n];
            }
            if (j == cost.length + n) return i;
            hasGas = 0;
        }

        return -1;
    }

}
