package alg_02_train_wyj._26_day_动态规划一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 14:17
 * @Version 1.0
 */
public class _02_322_CoinChange1 {
    private int minCoins = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        dfs(coins, amount, new ArrayList<>());
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    private void dfs(int[] coins, int amount, List<Integer> list) {
        if (amount == 0) {
            minCoins = Math.min(minCoins, list.size());
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] < 0) continue;
            list.add(coins[i]);
            dfs(coins, amount - coins[i], list);
            list.remove(list.size() - 1);
        }
    }
}
