package alg_02_train_dm._27_day_动态规划二_二刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 10:39
 * @Version 1.0
 */
public class _06_CostKnapsack {

    /*
        一维背包问题：选择物品只有一种代价，如：重量
        二维背包问题：
            有 n 种物品，每种物品只有 1 件，选择一种物品必须付出两种代价
            第 i 种物品第一种代价值是 w[i]，第二种代价值是 g[i]，物品的价值是 v[i]
            对于每种代价都有一个可付出的最大值 W 和 G (两种背包容量)
            问怎样选择物品可以得到最大的价值
 */
    public int knapsack(int[] w, int[] g, int W, int G, int[] v) {

        // dp[i][j]：表示选择物品时，付出两种代价分别为 i 和 j 可获得的最大价值
        int[][] dp = new int[W + 1][G + 1];

        // 两种代价：状态转移方程
        for (int i = 0; i < w.length; i++) {
            for (int j = W; j >= w[i]; j--) {
                for (int k = G; k >= g[i]; k--) {
                    // dp 中索引为代价的循环变量 j，k，不是 i，j
                    dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k - g[i]] + v[i]);
                }
            }
        }
        return dp[W][G];

        /*
              KeyPoint 区别：一维 0-1 背包代码 => 只有一种代价，容量 capacity
              for (int i = 0; i < n; i++) {
                  for (int j = capacity; j >= w[i]; j--) {
                      dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
                  }
              }
         */

    }
}
