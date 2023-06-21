package alg_02_train_dm._27_day_动态规划二_2刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 20:27
 * @Version 1.0
 */
public class _06_CompleteKnapsack4 {

    // 完全背包代码模板 => 后续很多算法题都可以转成完全背包问题 => 熟练掌握
    public int knapsackComplete(int[] w, int[] v, int capacity) {
        int n = w.length;
        // 1. 状态定义：
        // dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[capacity + 1];

        // 2. 状态初始化
        // => 合并到状态转移中

        // 3. 状态转移
        for (int i = 0; i < n; i++) {

            // 没有优化 count 前，其中 k 控制 dp[j - k * w[i]]，，保证数组不越界
            // 优化 count 后，需要通过 j 控制，j 从 w[i] 开始，保证 dp[j - w[i]] 数组不越界
            for (int j = w[i]; j <= capacity; j++) {

                // 结论
                // 1.完全背包中，放第 1 个物品产生的价值永远 >= 放第 2、3、4、5....个物品产生的的价值
                // 2.若放第 1 个物品产生的价值 比 不放这个物品产生的价值 要小，则那么不放物品产生的价值最大

                // 利用结论 1 => 将 for 循环去掉，减少重复计算，降低时间复杂度
//                int count = j / w[i];
//                for (int k = 0; k <= count; k++)

                // 利用结论 2 => 转成 0-1 背包问题
                // 1.dp[j] => 不放当前第 1 个物品
                // 2.v[i] + dp[j - w[i]] => 放当前第 1 个物品

                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack4 k = new _06_CompleteKnapsack4();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10));
    }
}
