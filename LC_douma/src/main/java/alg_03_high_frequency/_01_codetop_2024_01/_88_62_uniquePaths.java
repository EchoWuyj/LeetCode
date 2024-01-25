package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _88_62_uniquePaths {
    public int uniquePaths(int m, int n) {

        // 类似于最小路径和
        // dp[i][j]：表示从位置 [0, 0] 到 [i, j] 的路径数
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
