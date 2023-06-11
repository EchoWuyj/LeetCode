package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-18 13:48
 * @Version 1.0
 */

// 有效的数独
public class Problem_0036_ValidSudoku {
    /*
        请你判断一个9x9的数独是否有效
        只需要根据以下规则,验证已经填入的数字是否有效即可。

        数字 1-9 在每一行只能出现一次
        数字 1-9 在每一列只能出现一次
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次

        9行 1-9数字 是否出现
        row[0][8] 0行8数字,是否出现过
        row[i][j] i行j数字,是否出现过

        9列,9桶同理

     */
    public static boolean isValidSudoku(char[][] board) {
        // [9] 表示0-8行/列
        // [10] 表示1-9数字,其中0没有使用
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];

        // 0-8行
        for (int i = 0; i < 9; i++) {
            // 0-8列
            for (int j = 0; j < 9; j++) {
                // (i,j) i行j列 每个格子
                // 桶的编号
                // i行j列 => 桶的编号
                //      列决定左右位置
                //      行决定上下位置
                // 同一个bucket中9个位置的ij通过该公式都能得到唯一的bid
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    // board[i][j]一定是1-9中的数字
                    int num = board[i][j] - '0';
                    // i行是否为num,j列是否为num,bid号桶是否为num
                    // i行中1-9数字只能出现一次
                    // 如:3行3列出现4,3行4列出现4,这种情况则是不行的
                    // KeyPoint row是个二维数组,表示i和num所确定位置的boolean
                    // KeyPoint 注意if条件判断,||或者的条件,&&同时成立的条件,不要使用错了
                    //      这里是只要其中一个成立,则返回false,所以使用||,而不是&&
                    if (row[i][num] || col[j][num] || bucket[bid][num]) {
                        // 所有的位置都验证完后都没有返回false,则整体是验证通过的,最终返回true
                        return false;
                    }
                    // 说明i行,j列,bid号桶已经设置过该num了,为后面位置的if做铺垫
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
        return true;
    }
}
