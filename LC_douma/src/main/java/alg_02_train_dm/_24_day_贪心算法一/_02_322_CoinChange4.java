package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 15:29
 * @Version 1.0
 */
public class _02_322_CoinChange4 {
    private int minCoins = Integer.MAX_VALUE;

    // KeyPoint 回溯 + 贪心 => 存在 bug
    //  1.最开始确定 minCoins 不能保证是全局的 minCoins 还是要遍历所有节点，才能确定最少硬币数
    //  2.这里的贪心等于没有贪心，都是需要遍历所有节点，贪心在本题中没有提高性能
    public int coinChange(int[] coins, int amount) {
        // 升序排列
        Arrays.sort(coins);
        // 1. 回溯穷举所有的硬币组合

        dfs(amount, coins, new ArrayList<>());

//        dfs1(amount, coins, new ArrayList<>());

        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    // KeyPoint 方式一 返回值为 boolean，找到一个解，能提前退出
    // dfs 找到后，直接返回 true，结束 dfs 代码，不用往下遍历 => 提高新能(其实有 bug 的)
    private boolean dfs(int amount, int[] coins, List<Integer> path) {
        if (amount == 0) {
            // 只要最小值，并不需要路径 path
            minCoins = Math.min(minCoins, path.size());
            return true;
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            // 剪枝，注意这里只是跳过本次循环，不是 return false
            //      只有在 for 循环结束之后，再去 return false
            if (amount - coins[i] < 0) continue;
            path.add(coins[i]);
            // KeyPoint 若某个递归边界返回 true，返回上层 true 通过 if 判断，层层向上返回，从而提前结束递归
            if (dfs(amount - coins[i], coins, path)) return true;
            path.remove(path.size() - 1);
        }
        // for 循环结束都没有找到，直接返回 false
        return false;
    }

    // KeyPoint 方式二 返回值为 void，遍历所有节点
    private void dfs1(int amount, int[] coins,
                      List<Integer> path) {
        if (amount == 0) {
            minCoins = Math.min(minCoins, path.size());
            return;
        }
        // 从最大的面值硬币开始 DFS，减少遍历节点的数量 => 贪心思想
        // KeyPoint 注意从后往前遍历，i--，不是 i++
        for (int i = coins.length - 1; i >= 0; i--) {
            if (amount - coins[i] < 0) continue;
            path.add(coins[i]);
            // KeyPoint 返回值为 void，即使从某个递归边界返回，还得继续遍历其他节点，直到所有节点都已经遍历
            // KeyPoint 递归调用，注意自身调用是 dfs1，不是 dfs，两者不要搞混淆了
            dfs1(amount - coins[i], coins, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] c = {1, 7, 10};
        System.out.println(new _02_322_CoinChange4().coinChange(c, 14));
        // 5 => 10，1，1，1，1 即存在 bug，最少硬币应该是 2 个 7

        // KeyPoint 核心思想
        //  1.每次都贪心选择最优，最后不一定得到全局最优解，通过回溯得到全局最优解
        //  2.回溯算法中引入贪心思想，实现剪枝，降低时间复杂度，提高回溯性能
    }
}
