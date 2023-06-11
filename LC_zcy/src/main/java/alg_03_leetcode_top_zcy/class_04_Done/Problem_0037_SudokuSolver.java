package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-18 15:07
 * @Version 1.0
 */

// 解数独
public class Problem_0037_SudokuSolver {
    /*
        编写一个程序,通过填充空格来解决数独问题
        规则如上题
        题目数据保证输入数独仅有一个解
     */
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        initMaps(board, row, col, bucket);
        // 主函数从(0,0),(0,1)位置依次去判断
        process(board, 0, 0, row, col, bucket);
    }

    // 先初始化数独
    public void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + j / 3;
                // 根据board[][]中实际情况来设置row,col,bucket
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

    // KeyPoint 考察点:将剪枝条件写清楚的能力
    // 在每个位置进行深度优先遍历
    // 当前来到(i,j)这个位置,如果已经有数字,跳到下一个位置上;
    // 					   如果没有数字,则可以尝试1-9,不能和row,col,bucket信息冲突(剪枝的条件)
    // KeyPoint 因为题目数据保证输入数独仅有一个解,这里使用boolean作为返回值,在代码中作为判断递归的边界
    public boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        // 有效行号是0-8,都已经来到9行,表示之前做的决定找到有效的,返回true
        if (i == 9) {
            return true;
        }

        // 当离开(i,j)位置,下个位置(nexti,nextj),行优先的顺序执行
        //  → 第1行执行完
        //  → 执行第2行
        //  → 再执行3行

        // j没有达到最后1列(索引8位置),i不变;达到最后1列,i切下一行
        // j没有达到最后1列(索引8位置),j++,达到最后1列,j变成0,又变成最左的位置
        int nexti = j != 8 ? i : i + 1;
        // KeyPoint 单独一个操作是不能使用j++的,需要使用j+1
        int nextj = j != 8 ? j + 1 : 0;
        // 当前位置不是.,不能填入数字,直接去下个位置
        if (board[i][j] != '.') {
            // KeyPoint if分支进行深度优先遍历
            //  这里需要return,否则不是有效的递归
            return process(board, nexti, nextj, row, col, bucket);
        } else {
            // 可以尝试填入数字1-9
            int bid = 3 * (i / 3) + (j / 3);
            // 写代码需要小心,num是要取等到9的
            for (int num = 1; num <= 9; num++) {
                // i行是不为 num,j列是不为num,bid号桶是不为num
                if ((!row[i][num]) && (!col[j][num]) && (!bucket[bid][num])) {
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                    // board[i][j]设置正确数字字符
                    // KeyPoint '0'字符的基础上加
                    //  int i = 3 + '0';默认返回值为int
                    //  区别于 int num = board[i][j] - '0'
                    board[i][j] = (char) (num + '0');

                    // KeyPoint else分支进行深度优先遍历,故两个分支中都是进行深度优先遍历
                    /*
                              A
                           B  C  D

                        深度优先遍历,A->B,B过程结束(返回值不管是true或是false)都是要返回到A的,B->A
                        故这里不能直接return process(board, nexti, nextj, row, col, bucket),而是需要if判断
                        同时if后需要将现场还原,方便A->C的判断

                     */
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        return true;
                    }
                    // 深度优先遍历中返回上层,选择新的分支之前,需要将现场还原
                    // 当前位置是(nexti,nextj),则上个位置是(i,j)
                    // KeyPoint  错误写法 row[i][num] == false; ==判等,=赋值
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bid][num] = false;
                    // 上个位置是(i,j)设置成没有字符的样子
                    board[i][j] = '.';
                }
            }
        }

        // 1-9验证成功,返回true,否则返回false
        return false;
    }

    public static void main(String[] args) {

        System.out.println((3 + '0'));
    }
}
