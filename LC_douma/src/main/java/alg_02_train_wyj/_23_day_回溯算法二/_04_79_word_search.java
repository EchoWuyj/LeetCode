package alg_02_train_wyj._23_day_回溯算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 17:09
 * @Version 1.0
 */
public class _04_79_word_search {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private boolean[][] visited;
    private int rows;
    private int cols;
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        this.rows = board.length;
        this.cols = board[0].length;
        this.board = board;
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0))
                    if (dfs1(word, 0, i, j)) {
                        return true;
                    }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word,
                       int m, int n, int row, int col, int index) {
        if (index == word.length() - 1) return true;
        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m
                    && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]
                    && board[nextRow][nextCol] == word.charAt(index + 1)) {
                if (dfs(board, word, m, n, nextRow, nextCol, index + 1)) return true;
            }
        }
        visited[row][col] = false;
        return false;
    }

    // KeyPoint dfs 另外一种方式
    public boolean dfs1(String word, int index, int i, int j) {
        if (index == word.length()) return true;
        if (!inArea(i, j) || visited[i][j] || board[i][j] != word.charAt(index)) return false;

        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (dfs1(word, index + 1, nexti, nextj)) return true;
        }
        visited[i][j] = false;
        return false;
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
