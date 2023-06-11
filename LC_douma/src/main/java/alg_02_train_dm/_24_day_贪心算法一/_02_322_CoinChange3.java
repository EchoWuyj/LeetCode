package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 15:29
 * @Version 1.0
 */
public class _02_322_CoinChange3 {

    // KeyPoint minCoins 并不是每次递归都会使用，只是在递归边界才判断，定义成全局变量比较合适
    // 最小硬币数量
    private int minCoins = Integer.MAX_VALUE;

    // KeyPoint 优化回溯，不需要具体路径 path => 超出时间限制
    public int coinChange(int[] coins, int amount) {
        // 1. 回溯穷举所有的硬币组合
        dfs(amount, coins, new ArrayList<>());
        // 不存在，返回 -1
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    private void dfs(int amount, int[] coins,
                     List<Integer> path) {
        if (amount == 0) {
            // 只要最小值，并不需要路径 path
            minCoins = Math.min(minCoins, path.size());
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            // 剪枝，跳过本次循环
            if (amount - coins[i] < 0) continue;
            path.add(coins[i]);
            dfs(amount - coins[i], coins, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] c = {1, 2, 5};
        System.out.println(new _02_322_CoinChange3().coinChange(c, 12));
        // 3
    }
}
