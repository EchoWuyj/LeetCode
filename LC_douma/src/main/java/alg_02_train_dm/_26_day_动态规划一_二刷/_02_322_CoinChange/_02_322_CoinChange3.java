package alg_02_train_dm._26_day_动态规划一_二刷._02_322_CoinChange;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange3 {

    // 回溯 + 记忆化搜索
    public int coinChange(int[] coins, int amount) {
        // 0 ~ amount，一共 amount + 1 个状态
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(amount, coins, memo);
    }

    // 递归函数含义：计算返回凑成总金额 amount 需要的最少硬币数
    // 因为 amount 可能会重复，所以使用记忆化搜索，减少重复计算
    private int dfs(int amount, int[] coins, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        // memo 记录：凑成总金额 amount 需要的最少硬币数
        // 1.若 memo[amount] != Integer.MAX_VALUE(初始化值)，则说明该节点已经计算，直接返回即可
        // 2.若 memo[amount] == Integer.MAX_VALUE，则说明该节点没有计算过，则需要计算
        if (memo[amount] != Integer.MAX_VALUE) {
            return memo[amount];
        }
        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] < 0) continue;
            int subMinCoins = dfs(amount - coins[i], coins, memo);
            if (subMinCoins == -1) continue;
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
        // 在多叉树都遍历结束之后，得到 minCoins，再去设置 memo[amount]
        memo[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return memo[amount];
    }
}
