package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:42
 * @Version 1.0
 */
public class _72_64_minPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        // 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) {
                    // 即使 i 和 j 为特殊情况，也不需要明确 i 和 j 为 0，if 条件中已经限制了
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (i != 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i != 0 && j != 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
