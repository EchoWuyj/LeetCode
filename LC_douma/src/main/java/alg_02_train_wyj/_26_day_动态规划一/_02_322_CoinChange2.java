package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 16:48
 * @Version 1.0
 */
public class _02_322_CoinChange2 {

    public int coinChange(int[] coins, int amount) {
        return dfs(coins, amount);
    }

    public int dfs(int[] coins, int target) {
        if (target == 0) return 0;

        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (target - coins[i] < 0) continue;
            int subMinCoins = dfs(coins, target - coins[i]);
            if (subMinCoins == -1) continue;
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
}
