package com.jms.回溯;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/11 10:48
 */
public class _剑指_Offer_12_矩阵中的路径 {

    public static void main(String[] args) {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}};
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "AAB";
        _剑指_Offer_12_矩阵中的路径 v = new _剑指_Offer_12_矩阵中的路径();
        System.out.println(v.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == word.charAt(0)) {
                    boolean flag = dfs(board, word, r, c, 0);
                    if (flag) return flag;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        if (i == word.length()) return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return false;
        if (board[r][c] != word.charAt(i)) return false;
        char temp = board[r][c];
        board[r][c] = ' ';

        boolean dfs = dfs(board, word, r + 1, c, i + 1);
        if (dfs) return true;
        boolean dfs1 = dfs(board, word, r - 1, c, i + 1);
        if (dfs1) return true;
        boolean dfs2 = dfs(board, word, r, c - 1, i + 1);
        if (dfs2) return true;
        boolean dfs3 = dfs(board, word, r, c + 1, i + 1);
        if (dfs3) return true;

        board[r][c] = temp;

        return false;
    }
}
