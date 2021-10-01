package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/6 20:17
 */
public class _200_岛屿数量1 {
    public static void main(String[] args) {
        char[][] ints = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};

        char[][] ints1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        char[][] ints2 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };

        int result = numIslands(ints);

        System.out.println(result);
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int result = 0, rL = grid.length, cL = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);

        for (int i = 0; i < rL; i++) {
            for (int j = 0; j < cL; j++) {
                if (grid[i][j] == '1'){
                    if (i - 1 >= 0 && grid[i - 1][j] == '1'){
                        unionFind.union(i * cL + j, (i - 1) * cL + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1'){
                        unionFind.union(i * cL + j, i * cL + j - 1);
                    }
                    if (i + 1 < rL && grid[i + 1][j] == '1'){
                        unionFind.union(i * cL + j, (i + 1) * cL + j);
                    }
                    if (j + 1 < cL && grid[i][j + 1] == '1'){
                        unionFind.union(i * cL + j, i * cL + j + 1);
                    }
                }
            }
        }

        return unionFind.getCount();
    }

    static class UnionFind{
        private int count;
        private int[] rank;
        private int[] parents;

        UnionFind(char[][] grid){
            count = 0;
            int rL = grid.length;
            int cL = grid[0].length;
            int capacity = cL * rL;
            rank = new int[capacity];
            parents = new int[capacity];

            for (int i = 0; i < rL; i++) {
                for (int j = 0; j < cL; j++) {
                    if (grid[i][j] == '1'){
                        parents[i * cL + j] = i * cL + j;
                        count++;
                    }
                    rank[i * cL + j] = 0;
                }
            }
        }

        /**
         * 查找i的根节点
         */
        public int find(int i){
            rankCheck(i);
            while (i != parents[i]){
                parents[i] = parents[parents[i]];
                i = parents[i];
            }
            return i;
        }

        /**
         * 合并v1，v2两个岛屿
         */
        public void union(int v1, int v2){
            int root1 = find(v1);
            int root2 = find(v2);

            if (root1 == root2) return;

            if (rank[root1] < rank[root2]){
                parents[root1] = root2;
            }else if (rank[root1] > rank[root2]){
                parents[root2] = root1;
            }else {
                parents[root1] = root2;
                rank[root2] += 1;
            }
            count--;
        }

        public int getCount(){
            return count;
        }

        private void rankCheck(int i) {
            if (i < 0 || i >= parents.length){
                throw new IllegalArgumentException("传入的下表越界！");
            }
        }
    }




}
