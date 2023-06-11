package alg_02_train_dm._26_day_动态规划一._02_322_CoinChange;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange4 {

    // DP
    // O(k * n) n 表示硬币的种类
    public int coinChange(int[] c, int k) {
        if (k < 0) return -1;
        if (k == 0) return 0;

        // 1. 状态定义：dp[i] 表示凑齐总金额为 i 的时候需要的最小硬币数
        int[] dp = new int[k + 1];

        // 2. 状态初始化
        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 3. 状态转移
        // dp[target]，其中 target 从 1 到 k，表示每种状态
        for (int target = 1; target <= k; target++) {
            // 遍历硬币，使用每种硬币进行尝试
            for (int coin : c) {
                // target - coin 无法通过硬币凑齐，则 dp[target - coin] 为 Integer.MAX_VALUE
                if (target >= coin && dp[target - coin] != Integer.MAX_VALUE) {
                    // 无法进行状态数组的空间压缩，因为不确定和那个状态 dp[target - coin] 有关系
                    dp[target] = Math.min(dp[target], dp[target - coin] + 1);
                }
            }
        }

        // 4. 返回最终需要的状态值
        return dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
    }
}
