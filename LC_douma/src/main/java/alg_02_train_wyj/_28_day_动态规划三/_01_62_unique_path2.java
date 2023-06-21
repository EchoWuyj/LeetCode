package alg_02_train_wyj._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-20 23:13
 * @Version 1.0
 */
public class _01_62_unique_path2 {

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if (i != m - 1 && j == n - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    dp[j] = dp[j + 1];
                } else if (i != m - 1 && j == n - 1) {
                    dp[j] = dp[j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[j] = dp[j] + dp[j + 1];
                }
            }
        }
        return dp[0];
    }
}
