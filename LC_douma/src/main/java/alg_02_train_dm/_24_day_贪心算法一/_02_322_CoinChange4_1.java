package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 15:29
 * @Version 1.0
 */
public class _02_322_CoinChange4_1 {
    private int minCoins = Integer.MAX_VALUE;

    // KeyPoint 回溯 + 贪心 => 存在 bug
    // 贪心策略：从最大硬币开始选择 => 通过贪心给回溯剪枝，提升性能
    public int coinChange(int[] coins, int amount) {
        // KeyPoint 升序排列 => 从最大硬币开始选择 => 贪心策略
        Arrays.sort(coins);
        // 回溯穷举所有的硬币组合
        dfs(amount, coins, new ArrayList<>());
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    // KeyPoint 方式一 返回值为 boolean，若找到一个解，则能提前退出
    // dfs 找到后，直接返回 true，结束 dfs 代码，不用往下遍历，提高新能 => 其实有 bug 的
    private boolean dfs(int amount, int[] coins, List<Integer> path) {
        if (amount == 0) {
            // 只要最小值，并不需要路径 path
            minCoins = Math.min(minCoins, path.size());
            return true;
        }
        int n = coins.length;
        for (int i = n - 1; i >= 0; i--) {
            // KeyPoint 优化：剪枝
            // 1.注意：这里只是跳过本次循环，不是 return false
            // 2.只有在整体 for 循环结束之后，再去 return false
            if (amount - coins[i] < 0) continue;
            path.add(coins[i]);

            // KeyPoint 注意事项
            // 因为递归函数返回值为 boolean，故不能直接执行 dfs(amount - coins[i], coins, path)，而是外层加一个 if 判断
            // 一旦某次 dfs 递归，其值为 true，if 条件成立，层层向上返回，从而提前结束递归
            if (dfs(amount - coins[i], coins, path)) return true;
            path.remove(path.size() - 1);
        }
        // for 循环结束都没有找到，直接返回 false
        return false;
    }

    public static void main(String[] args) {
        int[] c = {1, 7, 10};
        System.out.println(new _02_322_CoinChange4_1().coinChange(c, 14));
        // KeyPoint 存在 bug
        // 5 => 10，1，1，1，1 ，但实际上最少硬币应该是 2 个 7

        // KeyPoint 总结：贪心和回溯的关系
        // 1.每次都贪心选择最优，最后不一定得到全局最优解，通过回溯得到全局最优解
        // 2.回溯算法中引入贪心思想，实现剪枝，降低时间复杂度，提高回溯性能
    }
}
