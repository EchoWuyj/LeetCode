package alg_02_train_dm._26_day_动态规划一._02_322_CoinChange;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange3 {

    // 回溯 + 记忆化搜索
    public int coinChange(int[] c, int k) {
        int[] memo = new int[k + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(k, c, memo);
    }

    // 计算返回凑成总金额 target 需要的最少硬币数
    // 因为 target 可能会重复，所以使用记忆化搜索，减少重复计算
    private int dfs(int target, int[] c, int[] memo) {
        if (target == 0) {
            return 0;
        }
        if (memo[target] != Integer.MAX_VALUE) {
            return memo[target];
        }
        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < c.length; i++) {
            // 注意：使用 target - c[i]，而不是 c[i] - target
            if (target - c[i] < 0) continue;
            int subMinCoins = dfs(target - c[i], c, memo);
            if (subMinCoins == -1) continue;
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
        // 在多叉树都遍历结束之后，得到 minCoins，再去设置 memo[target]
        memo[target] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[target];
    }
}
