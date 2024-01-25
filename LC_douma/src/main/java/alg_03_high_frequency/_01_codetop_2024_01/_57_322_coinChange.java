package alg_03_high_frequency._01_codetop_2024_01;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 23:03
 * @Version 1.0
 */
public class _57_322_coinChange {

    public int coinChange(int[] coins, int amount) {
        // 完全背包问题
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // 1.先物品
        for (int i = 0; i < n; i++) {
            // 2.再容量
            for (int j = coins[i]; j <= amount; j++) {
                // 注意：在 dp 数组中 j - coins[i]，而不是 dp[j] - coins[i]，两者不要混淆
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
