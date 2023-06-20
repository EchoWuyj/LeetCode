package alg_02_train_dm._27_day_动态规划二._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 21:25
 * @Version 1.0
 */
public class _06_0_1_Knapsack3 {

    // 动态规划
    public int knapsack(int[] w, int[] v, int capacity) {

        // 1.状态定义
        // dp[i][j] 表示从 [0...i] 号(物品索引)取物品放入容量为 j 的背包中的最大价值
        // 注意：容量是包括 capacity，故数组长度需要加 1
        int n = w.length;
        int[][] dp = new int[n][capacity + 1];

        // 2.状态初始化
        // 考虑将第 0 号物品放入背包中，此时只有一个 0 号物品可以选择
        // i 遍历每个容量，总容量为 capacity
        for (int i = 0; i <= capacity; i++) {
            // 容量 i 足够放，则设置 0 号物品的 dp
            // 注意：v[0]，只能是 0，表示 0 号物品，不能是 v[i]
            // => 固定行，变化只能是列 i
            dp[0][i] = (i >= w[0] ? v[0] : 0);
        }

        // 3.状态转移
        // 通过填表，分析状态转移方程，总的原则：已知 => 未知
        // 从上往下，从左往右
        // 注意：dp 第一行已经确定了，i 从 1开始，否则 dp[i-1] 越界
        // 1.遍历物品
        for (int i = 1; i < n; i++) {
            // 2.遍历背包
            for (int j = 0; j <= capacity; j++) {
                // 1.物品不能放进背包，使用上一行 dp[i-1][j]，保证此时为最大价值
                if (j < w[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 2.物品能放进背包，有两种选择(0-1背包)
                    // 2.1 放 => 自身价值 v[i] + 使用上一行 i-1，j 减去 w[i] 后的 dp，dp[i-1][j-w[i]]
                    // 2.2 不放 => 直接使用上一行 dp[i-1][j]，即：[0..i-1]号物品，在容量为 j 的背包下的最大价值
                    dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }

        // 返回：[0...n-1] 号(物品索引)取物品放入容量为 capacity 的背包中的最大价值
        // KeyPoint 通过报错信息，推测可能 bug 位置
        // 若 dp[n - 1][capacity] 返回为 0，说明初值没有被覆盖
        // => 大概率是 for 没有遍历到 capacity => j < capacity，没有取等
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack3 k = new _06_0_1_Knapsack3();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};
        System.out.println(k.knapsack(w, v, 10)); // 35
    }
}
