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
        int size = 0;
        for (int i = 1; i < size; i++) {
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
            // 保存沿途路径代码写法
            res.add(new ArrayList<>(path));
            // KeyPoint 区别：05_112_PathSum
            // res.add(new ArrayList<>(path)) 后没有 return 语句
            return;
        }

        // 多少个硬币，就有多少种选择，每个硬币依次判断 => 多叉树分支选择
        // KeyPoint 本质：多叉树分支选择
        for (int i = 0; i < coins.length; i++) {
            path.add(coins[i]);
            dfs(amount - coins[i], coins, path, res);
            // KeyPoint 注意事项
            // 因为在每次 dfs 后面都有'还原现场'操作
            // => 故 res.add(new ArrayList<>(path)) 可以加上 return
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
