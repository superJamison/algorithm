package com.jms.矩阵;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/1 10:30
 */
public class _1436_旅行终点站 {
    public static void main(String[] args) {

    }

    public String destCity(List<List<String>> paths) {
        if (paths.size() == 1) return paths.get(0).get(1);

        Set<String> set = new HashSet<>();

        for (List<String> path : paths) {
            set.add(path.get(0));
        }

        for (List<String> path : paths) {
            if (!set.contains(path.get(1))){
                return path.get(1);
            }
        }
        return "";
    }
}
