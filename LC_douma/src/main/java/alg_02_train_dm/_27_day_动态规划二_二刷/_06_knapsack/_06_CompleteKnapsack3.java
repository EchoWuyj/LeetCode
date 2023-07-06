package alg_02_train_dm._27_day_动态规划二_二刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 20:27
 * @Version 1.0
 */
public class _06_CompleteKnapsack3 {

    // 优化：状态压缩
    public int knapsackComplete(int[] w, int[] v, int capacity) {
        // 1. 状态定义
        // dp[c]:将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[capacity + 1];

        // 2. 状态初始化
        // 考虑将第 0 号物品放入背包中
        for (int i = 0; i <= capacity; i++) {
            dp[i] = (i / w[0]) * v[0];
        }

        // 3. 状态转移
        for (int i = 1; i < w.length; i++) {
            // 对于完全背包，不用调整状态转移方向，详细解释见下方
            for (int j = 0; j <= capacity; j++) {
                int count = j / w[i];
                for (int k = 0; k <= count; k++) {
                    dp[j] = Math.max(dp[j], k * v[i] + dp[j - k * w[i]]);
                }
            }

            // 解释：为什么对于完全背包，不用调整状态转移方向 ?
            // 完全背包问题中，之前的状态改变，是因为将一个物品放入背包中导致的
            // 而完全背包问题中同一件物品可以使用多次，故在之前改变的状态的基础上，再加物品也可以的
            // 区别：0-1 背包问题，每个物品只能使用一次，后面状态受前面状态的影响

            // 总结：
            // 1.0-1 背包在遍历容量的时候是'从右往左'遍历
            // 2.完全背包是'从左往右'遍历

        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack3 k = new _06_CompleteKnapsack3();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10)); // 46
    }
}
