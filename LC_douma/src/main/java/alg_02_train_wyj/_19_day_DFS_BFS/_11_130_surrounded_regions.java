package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 23:41
 * @Version 1.0
 */
public class _11_130_surrounded_regions {

    private boolean[][] visited;
    private int rows;
    private int cols;

    private final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[rows - 1][j] == 'O') dfs(board, rows - 1, j);
        }

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][cols - 1] == 'O') dfs(board, i, cols - 1);
        }

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (!isValid(i, j) || visited[i][j] || board[i][j] == 'X') {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(board, nexti, nextj);
        }
    }

    public boolean isValid(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
