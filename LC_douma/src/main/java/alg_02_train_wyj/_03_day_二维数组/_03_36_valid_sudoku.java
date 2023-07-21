package alg_02_train_wyj._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 22:55
 * @Version 1.0
 */
public class _03_36_valid_sudoku {
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][] boxUsed = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';

                    if (rowUsed[i][num]) return false;
                    else rowUsed[i][num] = true;

                    if (colUsed[j][num]) return false;
                    else colUsed[j][num] = true;

                    int boxId = (j / 3) + (i / 3) * 3;

                    if (boxUsed[boxId][num]) return false;
                    else boxUsed[boxId][num] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] test = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        isValidSudoku(test);
    }
}
