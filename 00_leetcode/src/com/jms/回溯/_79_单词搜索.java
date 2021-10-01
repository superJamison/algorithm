package com.jms.回溯;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/14 20:18
 */
public class _79_单词搜索 {

    public static void main(String[] args) {
        char[][] board =
                {{'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}};
        boolean result = exist(board, "CFBA");
        System.out.println(result);
    }

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;

        boolean result = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result = dfs(i, j, 0, board, word);
                    if (result) return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(int i, int j, int index, char[][] board, String word) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || index < 0 || index >= word.length()) return false;

        if (board[i][j] == word.charAt(index)) {
            // 如果到了最后一个，就不用往后再继续查找了
            if (index == word.length() - 1){
                return true;
            }
            char temp = board[i][j];
            // 防止往回查找
            board[i][j] = '#';
            boolean dfs1 = dfs(i + 1, j, index + 1, board, word);
            boolean dfs2 = dfs(i - 1, j, index + 1, board, word);
            boolean dfs3 = dfs(i, j + 1, index + 1, board, word);
            boolean dfs4 = dfs(i, j - 1, index + 1, board, word);
            board[i][j] = temp;

            return dfs1 || dfs2 || dfs3 || dfs4;
        }

        return false;
    }

    private static boolean dfs1(int i, int j, int index, char[][] board, String word) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || index < 0 || index >= word.length()) return false;

        if (board[i][j] == word.charAt(index)) {
            // 如果到了最后一个，就不用往后再继续查找了
            if (index == word.length() - 1){
                return true;
            }
            char temp = board[i][j];
            // 防止往回查找
            board[i][j] = '#';
            boolean dfs1 = dfs(i + 1, j, index + 1, board, word);
            if (dfs1) {
                board[i][j] = temp;
                return true;
            }

            boolean dfs2 = dfs(i - 1, j, index + 1, board, word);
            if (dfs2) {
                board[i][j] = temp;
                return true;
            }
            boolean dfs3 = dfs(i, j + 1, index + 1, board, word);
            if (dfs3) {
                board[i][j] = temp;
                return true;
            }
            boolean dfs4 = dfs(i, j - 1, index + 1, board, word);
            if (dfs4) {
                board[i][j] = temp;
                return true;
            }
            board[i][j] = temp;

            return false;
        }

        return false;
    }
}
