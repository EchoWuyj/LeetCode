package alg_02_train_wyj._28_day_动态规划三;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-11 13:17
 * @Version 1.0
 */
public class _01_62_unique_path1 {

    private int m;
    private int n;
    private int res = 0;

    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        dfs(0, 0);
        return res;
    }

    public void dfs(int i, int j) {
        if (i >= m || j >= n) return;
        if (i == m - 1 && j == n - 1) res++;

        dfs(i + 1, j);
        dfs(i, j + 1);
    }

    public int uniquePaths1(int m, int n) {
        this.m = m;
        this.n = n;
        int[][] memo = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs1(0, 0, memo);
    }

    public int dfs1(int i, int j, int[][] memo) {
        if (i >= m || j >= n) return 0;
        if (i == m - 1 && j == n - 1) return 1;

        if (memo[i][j] != -1) return memo[i][j];

        int left = dfs1(i + 1, j, memo);
        int right = dfs1(i, j + 1, memo);

        memo[i][j] = left + right;

        return left + right;
    }
}
