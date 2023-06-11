package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 17:23
 * @Version 1.0
 */
public class _02_322_CoinChange4 {
    public int coinChange(int[] coins, int k) {
        if (k < 0) return -1;
        if (k == 0) return 0;

        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int target = 1; target <= k; target++) {
            for (int c : coins) {
                if (target >= c && dp[target - c] != Integer.MAX_VALUE) {
                    dp[target] = Math.min(dp[target], dp[target - c] + 1);
                }
            }
        }

        return dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
    }
}
