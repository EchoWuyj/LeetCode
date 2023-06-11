package alg_03_leetcode_top_zcy.class_14;

/**
 * @Author Wuyj
 * @DateTime 2023-03-06 10:52
 * @Version 1.0
 */

//  被围绕的区域
public class Problem_0130_SurroundedRegions {
    
    /*
        给你一个mxn的矩阵board,由若干字符'X'和'O',找到所有被'X'围绕的区域,并将这些区域里所有的'O'用'X'填充

        思路:
          没有被包围的O,要么在边界,要么与边界的O相连
          第一遍dfs数组,标记所有在边界的O及其相邻的O
          再次遍历数组,如果发现被标记,说明它没有被包围,将其复原成O
     */

    // KeyPoint 方法一
    public static void solve1(char[][] board) {
        boolean[] ans = new boolean[1];
        // 先标记
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    // 一开始默认是应该改成'X'
                    ans[0] = true;
                    // (i,j)上下左右判断后返回,递归结束
                    can(board, i, j, ans);
                    board[i][j] = ans[0] ? 'T' : 'F';
                }
            }
        }

        // 再统一修改
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char can = board[i][j];
                if (can == 'T' || can == 'F') {
                    board[i][j] = '.';
                    change(board, i, j, can);
                }
            }
        }
    }

    public static void can(char[][] board, int i, int j, boolean[] ans) {
        // 只要越界就表示'O'没有被'X'围住
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            // ans类似于全局公共变量
            // 感染'O'过程碰到越界,将其设置为false(不设置为'X')
            ans[0] = false;
            return;
        }
        if (board[i][j] == 'O') {
            // 将走过的'O'其变成'.',避免走回头路,只有还是'O'才会感染
            board[i][j] = '.';
            can(board, i - 1, j, ans);
            can(board, i + 1, j, ans);
            can(board, i, j - 1, ans);
            can(board, i, j + 1, ans);
        }
    }

    public static void change(char[][] board, int i, int j, char can) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return;
        }
        if (board[i][j] == '.') {
            board[i][j] = can == 'T' ? 'X' : 'O';
            change(board, i - 1, j, can);
            change(board, i + 1, j, can);
            change(board, i, j - 1, can);
            change(board, i, j + 1, can);
        }
    }

    // KeyPoint 方法二(推荐)
    // 从边界开始感染的方法,比第一种方法更好
    public static void solve2(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int N = board.length;
        int M = board[0].length;

        // 从board4边开始标记
        for (int j = 0; j < M; j++) {
            // 0行每列
            if (board[0][j] == 'O') {
                free(board, 0, j);
            }
            // N行每列
            if (board[N - 1][j] == 'O') {
                free(board, N - 1, j);
            }
        }

        // 优化:避免board边界4角重复判断,i范围直接从1到N-2即可
        for (int i = 0; i < N - 1; i++) {
            // 0列每行
            if (board[i][0] == 'O') {
                free(board, i, 0);
            }
            // M列每行
            if (board[i][M - 1] == 'O') {
                free(board, i, M - 1);
            }
        }

        // 遍历所有位置修改
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'F') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void free(char[][] board, int i, int j) {
        // board[i][j] != 'O' 递归中不是'O'直接跳过,提高效率
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'F';
        free(board, i + 1, j);
        free(board, i - 1, j);
        free(board, i, j + 1);
        free(board, i, j - 1);
    }
}
