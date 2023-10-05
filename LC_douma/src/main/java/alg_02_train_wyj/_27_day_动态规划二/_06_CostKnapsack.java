package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 12:24
 * @Version 1.0
 */
public class _06_CostKnapsack {

    public int knapsack(int[] w, int[] g, int W, int G, int[] v) {
        int n = w.length;
        int[][] dp = new int[W + 1][G + 1];
        for (int i = 0; i < n; i++) {
            for (int j = W; j >= w[i]; j--) {
                for (int k = G; k >= g[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k - g[i]] + v[i]);
                }
            }
        }
        return dp[W][G];
    }
}
