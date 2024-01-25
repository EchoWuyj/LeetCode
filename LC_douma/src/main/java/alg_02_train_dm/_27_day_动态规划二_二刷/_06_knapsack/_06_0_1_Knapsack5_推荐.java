package alg_02_train_dm._27_day_动态规划二_二刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 18:53
 * @Version 1.0
 */
public class _06_0_1_Knapsack5_推荐 {

    // KeyPoint 优化二：0-1 背包模板代码
    //  => 一定需要记住，后面很多题目都是基于该代码进行修改
    // => 闭着眼睛都得能写出来
    public int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        // 1. 状态定义
        // dp[c]：将物品放入容量为 c 的背包中产生的最大价值
        // 容量从 0 开始一直到 capacity，故 dp 数组大小为 capacity+1
        int[] dp = new int[capacity + 1];

        // 2. 状态初始化
        // 由于没有了 dp[i-1]，故将状态初始化过程并入状态转移中，for 循环 i 从 0 开始
//        for (int j = 0; j <= capacity; j++) {
//            dp[j] = (j >= w[0] ? v[0] : 0);
//        }

        // 3. 状态转移
        // 由于没有了 dp[i-1]，故将状态初始化过程并入状态转移中，for 循环 i 从 0 开始
        // KeyPoint i 遍历每个物品
        for (int i = 0; i < n; i++) {
            // 一定是从右往左遍历 => 从容量大的开始选择
            // 合并 if 条件： if (j >= w[i]) 到 for 循环中，简化代码
            for (int j = capacity; j >= w[i]; j--) {
                // dp[j] 容量为 j 的最大价值
                // 不选 => dp[j]
                // 选择 => dp[j - w[i]]
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);

                // 补充：合并状态初始化过程
                // 当 i = 0 时，且 j >= w[0]，j = capacity
                // dp[capacity] = Math.max(dp[capacity], v[0] + dp[j - w[0]]);
                // dp[j - w[0]] 为前面位置 dp 状态，默认值为 0
                // => dp[capacity] = v[0]，
                // => j 依次减小，dp[j] 都赋值为 v[0]，从而实现了合并状态初始化过程
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack5_推荐 k = new _06_0_1_Knapsack5_推荐();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsack(w, v, 10)); // 35
    }
}
