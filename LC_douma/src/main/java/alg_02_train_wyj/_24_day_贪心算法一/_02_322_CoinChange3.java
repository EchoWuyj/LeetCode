package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 11:33
 * @Version 1.0
 */
public class _02_322_CoinChange3 {
    private int minCoins = Integer.MAX_VALUE;


    public int coinChange(int[] coins, int amount) {
        dfs(coins, amount, new ArrayList<>());
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    private void dfs(int[] coins, int amount, List<Integer> path) {
        if (amount == 0) {
            minCoins = Math.min(minCoins, path.size());
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] < 0) return;
            path.add(coins[i]);
            dfs(coins, amount - coins[i], path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] c = {1, 2, 5};
        System.out.println(new _02_322_CoinChange3().coinChange(c, 12));
        // 3
    }
}
