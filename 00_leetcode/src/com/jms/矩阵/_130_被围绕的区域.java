package com.jms.矩阵;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/10 20:25
 */
public class _130_被围绕的区域 {

    public static void main(String[] args) {
        char[][] chars =
                {{'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'X'}};

        char[][] chars1 =
                {{'O', 'X', 'X', 'O', 'X'},
                        {'X', 'O', 'O', 'X', 'O'},
                        {'X', 'O', 'X', 'O', 'X'},
                        {'O', 'X', 'O', 'O', 'O'},
                        {'X', 'X', 'O', 'X', 'O'}};
        solve(chars1);

        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars1[i].length; j++) {
                System.out.print(chars1[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 并查集
     * @param board
     */
    public static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (board == null || n == 0) return;
        UnionFind unionFind = new UnionFind(n * m + 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O'){
                    if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                        unionFind.union(i * m + j, n * m);
                    }else {
                        if (i - 1 >= 0 && board[i - 1][j] == 'O'){
                            unionFind.union(i * m + j, (i - 1) * m + j);
                        }
                        if (j - 1 >= 0 && board[i][j - 1] == 'O'){
                            unionFind.union(i * m + j, i * m + j - 1);
                        }
                        if (i + 1 < n && board[i + 1][j] == 'O'){
                            unionFind.union(i * m + j, (i + 1) * m + j);
                        }
                        if (j + 1 < m && board[i][j + 1] == 'O'){
                            unionFind.union(i * m + j, i * m + j + 1);
                        }
                    }
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (unionFind.find(i * m + j) == unionFind.find(n * m)) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * 深度优先算法
     * @param board
     */
    public static void solve2(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (board == null || n == 0) return;

        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }

        for (int j = 0; j < m; j++) {
            dfs(board, 0, j);
            dfs(board, n - 1, j);
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') return;

        board[i][j] = 'A';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    public static void solve1(char[][] board) {
        if (board == null || board.length == 0) return;

        int[] dx = {0, 0, -1, 1},
                dy = {-1, 1, 0, 0};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    if (dfs1(i, j, dx, dy, board)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

    }

    /**
     * 返回（i，j）位置是否可以将'O'转换为'X'
     */
    private static boolean dfs1(int i, int j, int[] dx, int[] dy, char[][] board) {

        boolean result = false;

        for (int index = 0; index < 4; index++) {
            int x = i + dx[index];
            int y = j + dy[index];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
                if (board[x][y] == 'X') {
                    result = true;
                } else if (board[x][y] == 'O') {
                    board[x][y] = 'X';
                    result = dfs1(x, y, dx, dy, board);
                    board[x][y] = 'O';
                }
                // 遇到一个不是
                if (!result) break;
            } else {
                return false;
            }
        }

        return result;
    }

    static class UnionFind {
        public int[] parents;
        public int[] rank;

        UnionFind(int capacity) {
            rank = new int[capacity];
            parents = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                rank[i] = 1;
                parents[i] = i;
            }
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
        }

        private void rankCheck(int i) {
            if (i < 0 || i >= parents.length) {
                throw new IllegalArgumentException("传入的下表越界！");
            }
        }
    }
}
