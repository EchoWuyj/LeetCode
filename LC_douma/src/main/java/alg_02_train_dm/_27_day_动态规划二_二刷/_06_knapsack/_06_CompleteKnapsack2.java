package alg_02_train_dm._27_day_动态规划二_二刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 18:55
 * @Version 1.0
 */
public class _06_CompleteKnapsack2 {

    public int knapsackComplete(int[] w, int[] v, int capacity) {

        int n = w.length;
        // 1. 状态定义
        // dp[i][c] 表示从 [0...i] 中选取物品放入容量为 capacity 的背包中的最大价值
        int[][] dp = new int[n][capacity + 1];

        // 2. 状态初始化
        // 考虑将第 0 号物品放入背包中
        for (int j = 0; j <= capacity; j++) {
            // 区别于：dp[0][j] = (j >= w[0] ? v[0] : 0)
            // 若 dp[0][j] 不设置，默认值即为 0，可以不单独定义
            // 因为每件物品可以取无限次，根据容量是物品 0 重量的倍数记录价值
            dp[0][j] = (j / w[0]) * v[0];
        }

        // 3. 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                int count = j / w[i];
                // 和 0-1 背包问题的不同，完全背包问题加了 k 倍，将 0-1 背包问题看成 k = 1
                // k 可以取等 count 的 => 选择 i 号物品，最多为 count ，最少为 0 个，一共 count + 1 种情况
                for (int k = 0; k <= count; k++) {

                    dp[i][j] = Math.max(dp[i][j], k * v[i] + dp[i - 1][j - k * w[i]]);

                    // dp[i][j] = Math.max(dp[i - 1][j], k * v[i] + dp[i - 1][j - k * w[i]]);

                    // 该代码：存在 bug！
                    // 完全背包涉及，count 的 for 循环，for 循环里面为的是获取最大值
                    // 而 dp[i][j] 并没有参与最大值的选择，即 dp[i][j] 可能被多次赋值
                    // 前期的较大值可能被后面较小值覆盖，不能保证 dp[i][j] 为最大值

                    // 为什么能将 dp[i-1][j] 替换成 dp[i][j]？
                    // 当 k = 0 时，dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j])
                    // 此时 dp[i][j] 已经记录了 dp[i - 1][j]，并没有将其漏掉。
                    // 同时，通过 dp[i][j] 和 k * v[i] + dp[i - 1][j - k * w[i]] 比较
                    // 从而获取最大值，赋值给 dp[i][j]
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack2 k = new _06_CompleteKnapsack2();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10)); // 46
    }
}
