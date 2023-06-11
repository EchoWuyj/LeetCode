package alg_02_体系班_zcy.class21;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 15:44
 * @Version 1.0
 */
public class Code05_BobDie {
    /*
        给定5个参数,N,M,row,col,k
        表示在N*M(10*5,行=>0~9,列=>0~4)的区域上,醉汉Bob初始在(row,col)位置
        Bob一共要迈出k步,且每步都会等概率向上下左右四个方向走一个单位
        任何时候Bob只要离开N*M的区域,就直接死亡,返回k步之后,Bob还在N*M的区域的概率

        本质 => 象棋跳马问题
     */

    public static double livePossibility1(int row, int col, int k, int N, int M) {
        // 不考虑棋盘边界,Bob所有走的情况数:4^k
        // 每走一步,都还在棋盘里,则生存点数加1,走完k步之后得总的生存点数
        // 生存概率=总的生存点数/4^k
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    // 目前在row,col位置,还有rest步要走,走完了如果还在棋盘中就获得1个生存点,返回总的生存点数
    public static long process(int row, int col, int rest, int N, int M) {
        // 越界没有生存点数
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }

        // 没有越界,还在棋盘中！
        if (rest == 0) {
            return 1;
        }
        // 还在棋盘中,还有步数要走,上下左右收集生存点数
        // 依赖关系:三维dp,上层依赖下层,同层之间不相互依赖
        long up = process(row - 1, col, rest - 1, N, M);
        long down = process(row + 1, col, rest - 1, N, M);
        long left = process(row, col - 1, rest - 1, N, M);
        long right = process(row, col + 1, rest - 1, N, M);
        return up + down + left + right;
    }

    public static double livePossibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    // 使用pick处理
    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePossibility1(6, 6, 10, 50, 50));
        System.out.println(livePossibility2(6, 6, 10, 50, 50));
    }
}
