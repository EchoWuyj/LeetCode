package alg_02_train_dm._22_day_回溯算法一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 21:32
 * @Version 1.0
 */
public class _15_37_SudokuSolver {
    /*
        37. 解数独
        编写一个程序，通过填充空格来解决数独问题。
        数独的解法需 遵循如下规则：
        数字 1-9 在每一行只能出现一次。
        数字 1-9 在每一列只能出现一次。
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
        数独部分空格内已填入了数字，空白格用'.'表示。
        
        提示：
        board.length == 9
        board[i].length == 9
        board[i][j] 是一位数字或者 '.'
        题目数据 保证 输入数独仅有一个解

     */

    // 思考方向：抽象成树形结构
    // 针对每行依次空白位置，使用 1-9 尝试，2-9 尝试 ...
    public void solveSudoku(char[][] board) {

        int n = board.length;
        // 用于标识数字是否在行、列、箱子中使用过
        // row index：行 或 列
        // col index：数字 1-9
        // value：是否存在，true 或 false
        boolean[][] rowUsed = new boolean[n][10];
        // boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[n][10];
        // 将一个宫格看做一个元素，故整体是一个 [3][3] 矩阵
        boolean[][][] boxUsed = new boolean[n / 3][n / 3][10];
        // boolean[][][] boxUsed = new boolean[3][3][10];

        // 初始化，二维数组已经有些数字
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 对于'.'不进行任何处理，在后面回溯过程进行填充
                if (board[row][col] != '.') {
                    // 将 字符数字 转成 int 数字
                    int num = board[row][col] - '0';
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    // board (3,3) -> box (1,1)
                    // 边长 3 作为一个整体，0,1,2 长度为 3，故 row / 3 = 0;
                    boxUsed[row / 3][col / 3][num] = true;
                }
            }
        }
        dfs(board, 0, 0, rowUsed, colUsed, boxUsed);
    }

    // 递归中，变化的形参为 col，故递归边界，使用 col 来判断
    // 返回值类型 boolean，标记回溯成功，或者失败
    // 回溯 backTrack
    private boolean dfs(char[][] board, int row, int col,
                        boolean[][] rowUsed,
                        boolean[][] colUsed,
                        boolean[][][] boxUsed) {

        // 递归边界
        // col == board[0].length
        // 即 col 到最后一列，处在越界位置，回溯
        if (col == board[0].length) {
            // 下一行
            row++;
            // 重置，第一列
            col = 0;
            // 已经到最后一行，回溯，返回 true
            if (row == board.length) {
                // 如果递归遍历到最后一行，中途都没有返回 false，则最终返回 true
                return true;
            }
        }

        // 该位置为 "."
        if (board[row][col] == '.') {
            // 对每个需要填充的位置，尝试使用 1 ~ 9 数字填充
            for (int num = 1; num <= 9; num++) {
                // 判断该位置是否能填充 num
                // 若其中一个为 true，则不能填入数组，则其反面可以填入数字
                boolean canPlaced = !rowUsed[row][num]
                        && !colUsed[col][num]
                        // boxUsed 的 row 和 col 需要 / 3，不能搞遗漏了
                        && !boxUsed[row / 3][col / 3][num];

                // 优化
//                boolean canPlaced = !(rowUsed[row][num]
//                        || colUsed[col][num]
//                        || boxUsed[row / 3][col / 3][num]);

                // 可以填充当前的空格
                if (canPlaced) {

                    // 填充数字
                    board[row][col] = (char) ('0' + num);
                    // 填入该数字后，标记不能填的行，列，box
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    // 尝试填充下一个位置，如果填充成功的话，则返回 true，失败返回 false
                    if (dfs(board, row, col + 1, rowUsed, colUsed, boxUsed)) {
                        return true;
                    }

                    // 1.若 dfs 返回 true，往下层继续 dfs，没有必要回溯
                    // 2.若 dfs 返回 false，不继续往下层 dfs，进行回溯，还原"现场"，改递归返回值类型
                    board[row][col] = '.';
                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else {
            // KeyPoint 一定得有 return，而不是单纯的 dfs，除了 void 可以不用 return，其他必有 return
            // 若 (row,col) 位置不为'.'，则跳过这个位置，直接到下一列
            return dfs(board, row, col + 1, rowUsed, colUsed, boxUsed);
        }

        // 递归过程中从 if 判断中，执行回溯，还原"现场"后
        // 返回 false，表示填充下个位置失败
        return false;
    }
}
