package alg_02_train_dm._27_day_动态规划二._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 21:10
 * @Version 1.0
 */
public class _06_CompleteKnapsack2_bak {

    // KeyPoint 使用 完全背包代码 来解决 0-1 背包问题
    public int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        // 1. 状态定义
        // dp[i][c] 表示从 [0...i] 中选取物品放入容量为 c 的背包中的最大价值
        int[][] dp = new int[n][capacity + 1];

        // 2. 状态初始化
        // 考虑将第 0 号物品放入背包 中
        for (int c = 0; c <= capacity; c++) {
            dp[0][c] = (c >= w[0] ? v[0] : 0);
        }

        // 3. 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 0-1 背包是 完全背包 的特例
                // 使用 完全背包 代码来解决 0-1 背包问题
                int count = j >= w[i] ? 1 : 0;
                for (int k = 0; k <= count; k++) {
                    dp[i][j] = Math.max(dp[i - 1][j], k * v[i] + dp[i - 1][j - k * w[i]]);
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack2_bak k = new _06_CompleteKnapsack2_bak();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsack(w, v, 10));
    }
}
