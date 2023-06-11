package alg_02_train_dm._27_day_动态规划二._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 18:53
 * @Version 1.0
 */
public class _06_0_1_Knapsack4 {

    // 优化一：压缩状态
    public int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        // 1. 状态定义
        // dp[c]：将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[capacity + 1];

        // 2. 状态初始化
        // 考虑将第 0 号物品放入背包中
        for (int i = 0; i <= capacity; i++) {
            dp[i] = (i >= w[0] ? v[0] : 0);
        }

        // 3. 状态转移
        // 压缩状态
        // => 若直接将行索引去掉，本题中是有问题的
        // => 即：后面 dp[j] 依赖前面的 dp[j - w[i]]，而 dp[j - w[i]] 发生了改变，从而导致了 dp[j] 出现错误
        // 想要效果：在计算当前状态值时，当前状态值所依赖的前面状态值，不能发生改变
        // => 解决措施：改变状态转移方向
        for (int i = 1; i < n; i++) {
            // 改变状态转移方向：从右往左
            // 这样计算后面的状态值，所依赖的前面状态值，不能发生改变，结果正确
            for (int j = capacity; j >= 0; j--) {
                // 优化：可以将 if 判断整合到 for 循环中 j >= 0 中，代码更简洁
                if (j >= w[i]) {
                    // 状态转移方程 => 后面状态 依赖 前面状态
                    // dp[j - w[i]] 里面是 w[i]，而不是 v[i]，不要混淆了
                    dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack4 k = new _06_0_1_Knapsack4();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsack(w, v, 10)); // 35
    }
}
