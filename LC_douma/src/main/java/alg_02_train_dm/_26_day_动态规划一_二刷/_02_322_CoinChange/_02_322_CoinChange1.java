package alg_02_train_dm._26_day_动态规划一_二刷._02_322_CoinChange;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange1 {

    /*
        322. 零钱兑换 -> 最优值问题 -> 动态规划求解

        给你一个整数数组 coins ，表示不同面额的硬币；
        以及一个整数 amount ，表示总金额。
        计算并返回可以凑成总金额所需的 最少的硬币个数 。
        如果没有任何一种硬币组合能组成总金额，返回 -1 。
        你可以认为 每种硬币的数量是无限的。

        KeyPoint 一开始并不知道该题使用动态规划来解，只有题目做的足够多了，
                 才能看出题目是否能使用动态规划求解

        示例 1：
        输入：coins = [1, 2, 5], amount = 11
        输出：3
        解释：11 = 5 + 5 + 1

        示例 2：
        输入：coins = [2], amount = 3
        输出：-1

        提示：
        1 <= coins.length <= 12
        1 <= coins[i] <= 2^31 - 1
        0 <= amount <= 10^4
     */

    // 直观想法：通过枚举方式，将所有硬币都组合起来，数值为 amount
    //          返回硬币数最少的组合，即为最终的结果
    private int minCoins = Integer.MAX_VALUE;

    // 类似路径和问题，amount 作为最终路径和，作为 root 接点值
    // => 回溯，抽象成树形结构
    // => 时间复杂度是指数级别，会超时
    public int coinChange(int[] coins, int amount) {
        // 回溯穷举所有的硬币组合
        dfs(amount, coins, new ArrayList<>());
        // 不存在情况
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    // dfs 前序遍历
    private void dfs(int target, int[] coins, List<Integer> list) {
        if (target == 0) {
            minCoins = Math.min(minCoins, list.size());
            return;
        }

        // 回溯，抽象成树形结构 => 将每个不同面额的硬币看成树的一个分支，对树进行遍历
        for (int i = 0; i < coins.length; i++) {
            // 剪枝
            if (target - coins[i] < 0) continue;
            list.add(coins[i]);
            dfs(target - coins[i], coins, list);
            // 回溯，将添加硬币移除，清除现场
            list.remove(list.size() - 1);
        }
    }
}
