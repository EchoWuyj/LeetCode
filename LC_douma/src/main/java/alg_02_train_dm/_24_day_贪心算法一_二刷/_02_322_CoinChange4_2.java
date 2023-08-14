package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-08-13 16:41
 * @Version 1.0
 */
public class _02_322_CoinChange4_2 {
    private int minCoins = Integer.MAX_VALUE;

    // KeyPoint 回溯 + 贪心 => 不能提升性能
    // 为了解决这个 bug
    // 1.最开始确定 minCoins 不能保证是全局的 minCoins 还是要遍历所有节点，才能确定最少硬币数
    // 2.这里的贪心等于没有贪心，都是需要遍历所有节点，贪心在本题中没有提高性能
    public int coinChange(int[] coins, int amount) {
        // KeyPoint 升序排列 => 没有有什么作用，所有节点都是要遍历的
        Arrays.sort(coins);
        // 回溯穷举所有的硬币组合
        dfs(amount, coins, new ArrayList<>());
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    // KeyPoint 方式二 返回值为 void，遍历所有节点
    private void dfs(int amount, int[] coins,
                     List<Integer> path) {
        if (amount == 0) {
            minCoins = Math.min(minCoins, path.size());
            return;
        }
        int n = coins.length;
        // 从最大的面值硬币开始 DFS，减少遍历节点的数量 => 贪心思想
        // 注意：从后往前遍历，i--，不是 i++
        for (int i = n - 1; i >= 0; i--) {
            // 优化：剪枝
            if (amount - coins[i] < 0) continue;
            path.add(coins[i]);
            // KeyPoint 注意事项
            // 1.返回值为 void，即使从某个递归边界返回，还得继续执行 for 循环，遍历其他节点，直到所有节点都已经遍历
            // 2.递归调用，注意自身调用是 dfs，别和其他方法名相混淆了
            dfs(amount - coins[i], coins, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] c = {1, 7, 10};
        System.out.println(new _02_322_CoinChange4_2().coinChange(c, 14));
        // KeyPoint 解决 bug
        // 2 => 7，7  => 最少硬币

    }
}
