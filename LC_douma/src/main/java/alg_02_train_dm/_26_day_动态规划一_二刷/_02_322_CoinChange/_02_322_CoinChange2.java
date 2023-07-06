package alg_02_train_dm._26_day_动态规划一_二刷._02_322_CoinChange;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange2 {

    // 另外一种方式回溯 => 类似于后序遍历 => 重点掌握
    public int coinChange(int[] coins, int amount) {
        return dfs(amount, coins);
    }

    // 在遍历每一个节点时，返回这个节点对应的总金额 amount 需要的最少硬币数
    // 递归返回值 int，和题目求解的问题：最少的硬币个数，保持一致
    private int dfs(int amount, int[] coins) {
        if (amount == 0) {
            // amount 为 0，此时已经不需要硬币，故返回 0
            return 0;
        }
        // 每一个节点，都定义一个 minCoins，记录该节点的的 minCoins，初始化为 Integer.MAX_VALUE
        int minCoins = Integer.MAX_VALUE;

        // for 循环相当于多叉树的分支，循环里面代码相当于处理一个分支逻辑
        for (int i = 0; i < coins.length; i++) {
            // 剪枝，直接跳过
            if (amount - coins[i] < 0) continue;

            // 子节点返回最小硬币个数
            int subMinCoins = dfs(amount - coins[i], coins);

            // 若返回 -1，不参与比较，跳过
            if (subMinCoins == -1) continue;

            // 比较左右子节点，取最小的硬币数，+1 表示加入父节点
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
        // amount > 0，但该节点没有分支，for 循环不执行，即凑不齐值为 amount 的硬币，返回 -1
        // minCoins 被赋初值，有可能始终没有被重新赋值，故不能直接返回 minCoins，需要判断一下
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
}
