package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 19:02
 * @Version 1.0
 */
public class _04_64_MinPathSum5 {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) {
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
