package alg_02_train_dm._27_day_动态规划二._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 18:53
 * @Version 1.0
 */
public class _06_0_1_Knapsack5 {

    // 优化二：0-1 背包模板代码
    //  => 一定需要记住，后面很多题目都是基于该代码进行修改
    public int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[capacity + 1];

        // 2. 状态初始化
        // 由于没有了 dp[i-1]，故将状态初始化过程并入状态转移中，for 循环 i 从 0 开始
//        for (int i = 0; i <= capacity; i++) {
//            dp[i] = (i >= w[0] ? v[0] : 0);
//        }

        // 3. 状态转移
        for (int i = 0; i < n; i++) {
            // 一定是从右往左遍历
            for (int j = capacity; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack5 k = new _06_0_1_Knapsack5();
        int[] w = {3, 4, 5, 2};
        int[] v = {15, 10, 12, 8};

        System.out.println(k.knapsack(w, v, 10)); // 35
    }
}
