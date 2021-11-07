package com.jms.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/6 16:54
 */
public class _118_杨辉三角 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(1);
        results.add(row1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> preLine = results.get(i - 1);
            for (int j = 0; j < (preLine.size() >> 1); j++) {
                list.add(preLine.get(j) + preLine.get(j + 1));
            }
            int size = list.size();
            if ((size & 1) == 1) {
                size--;
            }
            for (int j = size - 1; j >= 0; j--) {
                list.add(list.get(j));
            }
            results.add(list);
        }

        return results;
    }
}
