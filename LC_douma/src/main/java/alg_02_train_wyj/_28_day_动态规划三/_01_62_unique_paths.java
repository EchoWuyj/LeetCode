package alg_02_train_wyj._28_day_动态规划三;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-11 13:17
 * @Version 1.0
 */
public class _01_62_unique_paths {

    private int res;

    public int uniquePaths(int m, int n) {
        dfs(m, n, 0, 0);
        return res;
    }

    public void dfs(int m, int n, int i, int j) {

        if (i >= m || j >= n) {
            return;
        }

        if (i == m - 1 && j == n - 1) {
            res++;
        }

        dfs(m, n, i + 1, j);
        dfs(m, n, i, j + 1);
    }

    public int uniquePaths1(int m, int n) {
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs1(m, n, 0, 0, memo);
    }

    public int dfs1(int m, int n, int i, int j, int[][] memo) {
        if (i == m || j == n) return 0;
        if (i == m - 1 && j == n - 1) return 1;

        if (memo[i][j] != -1) return memo[i][j];

        int left = dfs1(m, n, i + 1, j, memo);
        int right = dfs1(m, n, i, j + 1, memo);
        memo[i][j] = left + right;

        return memo[i][j];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                } else {
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
                if (i == m - 1 || j == n - 1) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j] + dp[j + 1];
                }
            }
        }
        return dp[0];
    }

    public int uniquePaths4(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths5(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
