package alg_02_train_wyj._27_day_动态规划二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 18:12
 * @Version 1.0
 */
public class _07_322_coin_change {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
