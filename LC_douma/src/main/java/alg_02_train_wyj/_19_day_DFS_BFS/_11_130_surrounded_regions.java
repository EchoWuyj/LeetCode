package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 23:41
 * @Version 1.0
 */
public class _11_130_surrounded_regions {

    char[][] board;
    int rows;
    int cols;
    boolean[][] visited;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O' && !visited[0][j]) {
                dfs(0, j);
            }

            if (board[rows - 1][j] == 'O' && !visited[rows - 1][j]) {
                dfs(rows - 1, j);
            }
        }

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                dfs(i, 0);
            }

            if (board[i][cols - 1] == 'O' && !visited[i][cols - 1]) {
                dfs(i, cols - 1);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(int i, int j) {
        if (!inArea(i, j) || board[i][j] == 'X' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(nexti, nextj);
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
