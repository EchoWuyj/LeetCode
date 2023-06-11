package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-06-01 19:44
 * @Version 1.0
 */
public class _13_529_minesweeper {

    char[][] board;
    int rows;
    int cols;
    boolean[][] visited;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            dfs(click[0], click[1]);
        }
        return board;
    }

    public void dfs(int i, int j) {
        if (!inArea(i, j) || board[i][j] != 'E' || visited[i][j]) {
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
                dfs(nexti, nextj);
            }
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
