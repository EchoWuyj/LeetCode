package alg_02_train_dm._28_day_动态规划三_2刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-20 21:26
 * @Version 1.0
 */
public class _01_62_unique_paths3 {

    // KeyPoint 方法四  动态规划(右下到左上)
    public int uniquePaths4(int m, int n) {
        // dp[i][j]：表示从位置 [0, 0] 到 [i, j] 的路径数
        int[][] dp = new int[m][n];

        // 初始化：[0,0] 到 [0,0]，只有一条路径
        dp[0][0] = 1;

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // KeyPoint 优化 状态压缩
    public int uniquePaths5(int m, int n) {
        // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        int[] dp = new int[n];

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
