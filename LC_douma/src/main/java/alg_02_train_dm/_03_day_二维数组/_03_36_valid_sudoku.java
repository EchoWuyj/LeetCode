package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 22:01
 * @Version 1.0
 */
public class _03_36_valid_sudoku {
    /*
        36. 有效的数独
        请你判断一个 9 x 9 的数独是否有效。
        只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
        数字 1-9 在每一行只能出现一次。
        数字 1-9 在每一列只能出现一次。
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）

        注意：
        一个有效的数独（部分已被填充）不一定是可解的。
        只需要根据以上规则，验证已经填入的数字是否有效即可。
        空白格用 '.' 表示。

        提示：
        board.length == 9
        board[i].length == 9
        board[i][j] 是一位数字（1-9）或者 '.'

     */
    public boolean isValidSudoku(char[][] board) {
        // KeyPoint 通过额外数组来标记二维数组中元素出现与否
        // [i][j]，i 表示'行'或'列'，j 表示数字
        // 利用将元素值作为索引，从而高效判断数组中是否存在该元素
        boolean[][] rowUsed = new boolean[9][9];
        boolean[][] colUsed = new boolean[9][9];
        boolean[][] boxUsed = new boolean[9][9];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // 保证在不为 '.'，即是数字的情况下判断
                if (board[row][col] != '.') {
                    // 转索引，故需要减 1，同时
                    // KeyPoint 数字 ascii 值，不是字面数字值
                    //  1 '5' ascii 不是 5，而是 53
                    //  2 '5' - '0'，这样的 ascii 才是 5
                    //  int num = (int) board[row][col]; 53 数组肯定越界
                    int num = board[row][col] - '1';

                    // KeyPoint 多个 if 并行独立判断，不能是 if else 分支选择

                    // 判断行
                    if (rowUsed[row][num]) return false;
                    else rowUsed[row][num] = true;

                    // 判断列
                    if (colUsed[col][num]) return false;
                    else colUsed[col][num] = true;

                    /*
                    boxIndex 是如何推导出来的呢？

                    补充知识：二维数组转一维数组
                    二维数组中，有个元素坐标索引 (row,col)
                    => 一维数组中索引 index = col + row * n，(n 二维数组列数)

                    求解 boxIndex 过程
                    先将二维数组元素 (row,col)，转成这个元素所在的 box 的坐标索引为 (row/3,col/3)
                    然后将二维的 3×3 的 box 数组转成一维的，boxIndex = col / 3 + (row / 3) * 3
                    一般是通过'行优先方式计算'

                     */

                    // 判断 3*3 宫格
                    int boxIndex = col / 3 + (row / 3) * 3;
                    if (boxUsed[boxIndex][num]) return false;
                    else boxUsed[boxIndex][num] = true;
                }
            }
        }
        return true;
    }
}
