package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:42
 * @Version 1.0
 */
public class _72_64_minPathSum {

    // 最小路径和
    // 动态规划
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // 除 [0,0] 位置外，遍历二维矩阵的其余位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 即使 i 和 j 为特殊情况，也不需要明确 i 和 j 为 0，因为 if 条件中已经限制了
                if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (i != 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i != 0 && j != 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                } else {
                    // dp[0][0] 不用处理，最开始位置已经定义过了
                    // dp[0][0] = grid[0][0]
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
