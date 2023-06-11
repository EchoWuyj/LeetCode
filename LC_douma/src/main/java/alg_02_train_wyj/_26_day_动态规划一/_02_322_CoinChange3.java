package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 17:07
 * @Version 1.0
 */
public class _02_322_CoinChange3 {

    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(coins, amount, memo);
    }

    public int dfs(int[] coins, int target, int[] memo) {
        if (target == 0) return 0;
        if (memo[target] != Integer.MAX_VALUE) return memo[target];

        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (target - coins[i] < 0) continue;
            int subMinCoins = dfs(coins, target - coins[i], memo);
            if (subMinCoins == -1) continue;
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
        memo[target] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[target];
    }
}
