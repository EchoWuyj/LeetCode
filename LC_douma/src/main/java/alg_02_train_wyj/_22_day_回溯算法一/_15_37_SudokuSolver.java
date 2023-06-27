package alg_02_train_wyj._22_day_回溯算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 23:11
 * @Version 1.0
 */
public class _15_37_SudokuSolver {

    public void solveSudoku(char[][] board) {

        int n = board.length;
        boolean[][] rowUsed = new boolean[n][10];
        boolean[][] colUsed = new boolean[n][10];
        boolean[][][] boxUsed = new boolean[n / 3][n / 3][10];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != '.') {
                    int num = board[row][col] - '0';
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;
                }
            }
        }
        dfs(board, 0, 0, rowUsed, colUsed, boxUsed);
    }

    public boolean dfs(char[][] board, int row, int col,
                       boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed) {
        if (col == board[0].length) {
            row++;
            col = 0;
            if (row == board.length) return true;
        }

        if (board[row][col] == '.') {
            for (int num = 1; num <= 9; num++) {
                boolean isPlaced = !(rowUsed[row][num]
                        || colUsed[col][num]
                        || boxUsed[row / 3][col / 3][num]);
                if (isPlaced) {
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    board[row][col] = (char) ('0' + num);
                    if (dfs(board, row, col + 1, rowUsed, colUsed, boxUsed)) return true;

                    board[row][col] = '.';
                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else {
            return dfs(board, row, col + 1, rowUsed, colUsed, boxUsed);
        }
        return false;
    }
}
