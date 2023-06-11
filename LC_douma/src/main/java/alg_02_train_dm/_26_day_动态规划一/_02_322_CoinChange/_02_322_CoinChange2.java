package alg_02_train_dm._26_day_动态规划一._02_322_CoinChange;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:52
 * @Version 1.0
 */
public class _02_322_CoinChange2 {

    // 另外一种方式回溯，类似于后序遍历
    public int coinChange(int[] c, int k) {
        return dfs(k, c);
    }

    // 在遍历每一个节点的时候，返回这个节点对应的总金额需要的最少硬币数
    private int dfs(int target, int[] c) {
        if (target == 0) {
            // target 为 0，则不需要硬币，故返回 0
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;

        // for 循环相当于多叉树的分支，循环里面代码相当于处理一个分支逻辑
        for (int i = 0; i < c.length; i++) {
            // 剪枝，直接跳过
            if (target - c[i] < 0) continue;
            // 子节点返回最小硬币个数
            int subMinCoins = dfs(target - c[i], c);
            // 若返回 -1，不参与比较，跳过
            if (subMinCoins == -1) continue;
            // 比较左右子节点，取最小的硬币数
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
        // target > 0，但该节点没有分支，for 循环不执行，即凑不齐值为 target 的硬币，返回 -1
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
}
