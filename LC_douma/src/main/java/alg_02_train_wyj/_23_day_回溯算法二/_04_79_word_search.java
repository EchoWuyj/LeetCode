package alg_02_train_wyj._23_day_回溯算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 17:09
 * @Version 1.0
 */
public class _04_79_word_search {
    private char[][] board;
    private String word;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        this.m = board.length; // 行
        this.n = board[0].length; // 列
        this.visited = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == word.charAt(0)) {
                    if (dfs(row, col, 0)) return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, int index) {
        if (board[row][col] != word.charAt(index)) return false;
        else if (index == word.length() - 1) return true;
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]) {
                if (dfs(nextRow, nextCol, index + 1)) return true;
            }
        }
        visited[row][col] = false;
        return false;
    }
}
