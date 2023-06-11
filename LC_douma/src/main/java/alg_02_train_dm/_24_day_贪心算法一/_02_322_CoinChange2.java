package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 15:29
 * @Version 1.0
 */
public class _02_322_CoinChange2 {

    // KeyPoint 回溯 => 抽象树形结构 => 超出内存限制
    public int coinChange(int[] coins, int amount) {
        // 1.回溯穷举所有的硬币组合
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(amount, coins, path, res);

        // KeyPoint 判空，res 不一定都存在结果集，res 可能为空
        // 2.没有任何的硬币组合，返回 -1
        if (res.isEmpty()) return -1;

        // 3.找到适应硬币数最少的组合的硬币数
        int min = 0;
        for (int i = 1; i < res.size(); i++) {
            // res 后续元素 size < min 对应的 size，就更新 min
            if (res.get(i).size() < res.get(min).size()) {
                min = i;
            }
        }
        return res.get(min).size();
    }

    private void dfs(int amount, int[] coins,
                     List<Integer> path,
                     List<List<Integer>> res) {
        if (amount < 0) return;
        if (amount == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 多少个硬币就有多少种选择，每个硬币依次判断
        // KeyPoint 本质:多叉树
        for (int i = 0; i < coins.length; i++) {
            path.add(coins[i]);
            dfs(amount - coins[i], coins, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        test1(); // 3
        test2(); // 3
    }

    private static void test1() {
        int[] c = {1, 2, 5};
        System.out.println(new _02_322_CoinChange2().coinChange(c, 11));
    }

    private static void test2() {
        int[] c = {3, 5};
        System.out.println(new _02_322_CoinChange2().coinChange(c, 11));
    }
}
