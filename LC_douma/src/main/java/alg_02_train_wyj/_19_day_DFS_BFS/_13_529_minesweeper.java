package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-06-01 19:44
 * @Version 1.0
 */
public class _13_529_minesweeper {

    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1},
            {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
    private int rows;
    private int cols;

    public char[][] updateBoard(char[][] board, int[] click) {
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];

        int i = click[0];
        int j = click[1];

        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        } else {
            dfs(board, i, j);
        }
        return board;
    }

    public void dfs(char[][] board, int i, int j) {
        if (!inArea(i, j) || visited[i][j] || board[i][j] != 'E') {
            return;
        }
        visited[i][j] = true;
        int mines = 0;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (inArea(nexti, nextj) && board[nexti][nextj] == 'M') {
                mines++;
            }
        }

        if (mines > 0) {
            board[i][j] = (char) (mines + '0');
        } else {
            board[i][j] = 'B';
            for (int[] dir : dirs) {
                int nexti = i + dir[0];
                int nextj = j + dir[1];
                dfs(board, nexti, nextj);
            }
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
