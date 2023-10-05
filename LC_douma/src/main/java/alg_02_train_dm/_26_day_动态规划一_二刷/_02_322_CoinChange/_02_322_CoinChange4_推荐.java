package alg_02_train_dm._26_day_动态规划一_二刷._02_322_CoinChange;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange4_推荐 {

    // DP
    // O(amount * n)，其中 n 表示硬币的种类
    public int coinChange(int[] coins, int amount) {
        // KeyPoint DP中，需要增加判空条件，避免数组过小导致的越界
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        // 1. 状态定义
        // dp[i] 表示凑齐总金额为 i 的时候需要的最小硬币数
        int[] dp = new int[amount + 1];

        // 2. 状态初始化
        // 注意：因为要比较的是最小值，这个不可能的值，赋值成为一个最大值
        // 但是赋值 Integer.MAX_VALUE，特别小心，状态转移方程不要越界，即 Integer.MAX_VALUE + 1
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 3. 状态转移
        // dp[i]，其中 i 从 1 到 amount，表示每种状态
        for (int i = 1; i <= amount; i++) {
            // 遍历硬币，使用每种硬币进行尝试
            for (int coin : coins) {
                // i >= coin => 保证 dp[i - coin] 索引不越界
                // dp[i - coin] != Integer.MAX_VALUE => dp[i - coin] 存在最小硬币个数，其中该条件可以省略
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    // 注意：这里无法进行状态数组的空间压缩，因为不确定和那个状态 dp[i - coin] 有关系
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 4. 返回最终需要的状态值
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
