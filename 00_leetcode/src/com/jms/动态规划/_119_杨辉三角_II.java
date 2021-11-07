package com.jms.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/6 17:09
 */
public class _119_杨辉三角_II {

    // 数学 C[rowIndex, i] = C[rowIndex, i - 1] * (rowIndex - i + 1) / i
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            list.add((int) ((long) list.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return list;
    }

    public List<Integer> getRow1(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        for (int i = 0; i < rowIndex; i++) {
            int prev = 1;
            for (int j = 0; j < list.size() - 1; j++) {
                int cur = list.get(j + 1);
                list.set(j + 1, list.get(j + 1) + prev);
                prev = cur;
            }
            list.add(1);
        }

        return list;
    }
}
