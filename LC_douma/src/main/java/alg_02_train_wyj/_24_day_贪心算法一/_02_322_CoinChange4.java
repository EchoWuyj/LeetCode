package alg_02_train_wyj._24_day_贪心算法一;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 11:53
 * @Version 1.0
 */
public class _02_322_CoinChange4 {
    private int minCoins = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    private boolean dfs(int amount, int[] coins, List<Integer> path) {
        if (amount == 0) {
            minCoins = Math.min(minCoins, path.size());
            return true;
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            if (amount - coins[i] < 0) continue;
            path.add(coins[i]);
            if (dfs(amount - coins[i], coins, path)) return true;
            path.remove(path.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] c = {1, 7, 10};
        System.out.println(new _02_322_CoinChange4().coinChange(c, 14));
    }
}
