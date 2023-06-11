package alg_02_train_dm._27_day_动态规划二._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 10:36
 * @Version 1.0
 */
public class _06_MultipleKnapsack {
    /*
        多重背包：
            有 n 种物品和一个容量为 C 的背包
            第 i 种物品的重量是 w[i]，价值是 v[i]，件数是 p[i]
            求将哪些物品装入背包可使得价值总和最大
 */

    // 多重背包问题 => 转化成 0-1 背包问题
    public int knapsack1(int[] w, int[] v, int[] p, int capacity) {

        // 比如：
        // 2 件价值为 5，重量为 2 的同一物品，对应：p[i] = 2，v[i] = 5，w[i] = 2
        // 转化成 0-1 背包问题，即将同一物品看成不同的物品，即：物品 a 和物品 b
        // 物品 a 和 b 的价值都为 5，重量都为 2，从而变成了 0-1 背包问题

        int n = w.length;
        if (n == 0) return 0;

        // KeyPoint w 和 v 数组转换

        int newN = 0;
        for (int i = 0; i < n; i++) {
            // 统计物品总件数
            newN += p[i];
        }

        // 创建新的 W 和 V 数组
        int[] newW = new int[newN];
        int[] newV = new int[newN];

        int index = 0;
        // 将同一物品看成不同的物品，对 newW 和 newV 进行赋值
        for (int i = 0; i < n; i++) {
            // p[i]：i 索引物品的件数，循环 p[i] 次进行赋值
            for (int j = 0; j < p[i]; j++) {
                newW[index] = w[i];
                newV[index] = v[i];
                index++;
            }
        }

        // KeyPoint 利用 0-1 背包的模板代码

        // 1. 状态定义
        // dp[c]：将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[capacity + 1];

        // 2. 状态初始化

        // 3. 状态转移
        for (int i = 0; i < newN; i++) {
            for (int j = capacity; j >= newW[i]; j--) {
                dp[j] = Math.max(dp[j], newV[i] + dp[j - newW[i]]);
            }
        }

        // 4. 返回结果
        return dp[capacity];
    }

    // KeyPoint knapsack 方法实现有 bug，看上面 knapsack1 方法
    public int knapsack(int[] w, int[] v, int[] p, int C) {
        int len = w.length;
        if (len == 0) {
            return 0;
        }
        // 1. 状态定义：
        // dp[c]：将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[C + 1];

        // 2. 状态初始化

        // 3. 状态转移
        // 时间复杂度：
        for (int i = 0; i < len; i++) {
            for (int c = 0; c <= C; c++) {
                int count = Math.min(c / w[i], p[i]);
                for (int k = 0; k <= count; k++) {
                    dp[c] = Math.max(dp[c], k * v[i] + dp[c - k * w[i]]);
                }
            }
        }

        // 4. 返回结果
        return dp[C];
    }
}
