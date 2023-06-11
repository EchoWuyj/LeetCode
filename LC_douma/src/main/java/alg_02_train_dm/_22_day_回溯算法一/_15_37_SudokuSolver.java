package alg_02_train_dm._22_day_回溯算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 21:32
 * @Version 1.0
 */
public class _15_37_SudokuSolver {
    public void solveSudoku(char[][] board) {
        // 用于标识数字是否在行、列、箱子中使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        // 将一个宫格，整体看做一个元素
        boolean[][][] boxUsed = new boolean[3][3][10];

        // 初始化(二维数组已经有些数字)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // 将字符转成整型
                int num = board[i][j] - '0';
                // 对于'.'进行任何处理，在后面回溯过程进行填充
                if (num >= 1 && num <= 9) {
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    // board (3,3) -> box (1,1)
                    boxUsed[i / 3][j / 3][num] = true;
                }
            }
        }

        // bug 修复，需要调用回溯的方法
        backTrack(board, 0, 0, rowUsed, colUsed, boxUsed);
    }

    // backTrack 回溯，上机写代码直接使用 dfs，简单明了
    // 返回值类型 boolean，标记回溯成功，或者失败
    private boolean backTrack(char[][] board, int row, int col,
                              boolean[][] rowUsed,
                              boolean[][] colUsed,
                              boolean[][][] boxUsed) {
        // col 到最后一列
        // col == board[0].length 一定是处在越界位置，再去回溯
        if (col == board[0].length) {
            // 下一行
            row++;
            // 重置，第一列
            col = 0;
            // 已经到最后一行，回溯，返回 true
            if (row == board.length) {
                // 如果递归遍历到最后一个节点，中途都没有返回 false，则最终返回 true
                return true;
            }
        }

        // 该位置为 "."
        if (board[row][col] == '.') {
            // 对每个需要填充的位置，尝试使用 1 ~ 9 数字填充
            for (int num = 1; num <= 9; num++) {
                // 判断该位置是否能填充 num
                // 若其中一个为 true，则不能填入数组，则其反面可以填入数字
                boolean canPlaced = !(rowUsed[row][num]
                        || colUsed[col][num]
                        // boxUsed 的 row 和 col 需要 / 3，不能搞遗漏了
                        || boxUsed[row / 3][col / 3][num]);
                if (canPlaced) { // 可以填充当前的空格
                    // 填入该数字后，标记不能填的行，列，box
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    // 填入数字
                    board[row][col] = (char) ('0' + num);

                    // 只有在下一个位置填充失败，才进行回溯，
                    // 需要修改递归返回值类型，改成 boolean 类型

                    // 尝试填充下一个位置，如果填充成功的话，则返回 true
                    if (backTrack(board, row, col + 1, rowUsed, colUsed, boxUsed)) {
                        return true;
                    }
                    // backTrack 返回 false，if 不执行，则不继续往下层递归
                    // 回溯到上一层，还原"现场"
                    board[row][col] = '.';
                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else { // 跳过这个空格
            return backTrack(board, row, col + 1, rowUsed, colUsed, boxUsed);
        }

        // 递归过程中从 if 判断中，执行回溯，还原"现场"后
        // 返回 false，表示填充下个位置失败
        return false;
    }
}
