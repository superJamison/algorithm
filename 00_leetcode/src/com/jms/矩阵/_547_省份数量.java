package com.jms.矩阵;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/7 21:38
 */
public class _547_省份数量 {
    public static void main(String[] args) {
        int[][] ints = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};

        int[][] ints1 = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };

        /*
                1 0 1
                0 1 0
                1 0 1
         */
        int result = findCircleNum(ints1);
        System.out.println(result);
    }

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) return 0;

        Set<Integer> set = new HashSet<>();

        int r = isConnected.length;

        UnionFind unionFind = new UnionFind(r);

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.getCount();
    }

    static class UnionFind {
        public int[] parents;
        public int[] rank;
        public int count;

        UnionFind(int capacity) {
            count = capacity;
            rank = new int[capacity];
            parents = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                rank[i] = 1;
                parents[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        /**
         * 查找i的根节点
         */
        public int find(int i) {
            rankCheck(i);
            while (i != parents[i]) {
                parents[i] = parents[parents[i]];
                i = parents[i];
            }
            return i;
        }

        /**
         * 合并v1，v2两个岛屿
         */
        public void union(int v1, int v2) {
            int root1 = find(v1);
            int root2 = find(v2);

            if (root1 == root2) return;

            if (rank[root1] < rank[root2]) {
                parents[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parents[root2] = root1;
            } else {
                parents[root1] = root2;
                rank[root2] += 1;
            }
            count--;
        }

        private void rankCheck(int i) {
            if (i < 0 || i >= parents.length) {
                throw new IllegalArgumentException("传入的下表越界！");
            }
        }
    }

}
