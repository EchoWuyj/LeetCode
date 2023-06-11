package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 11:15
 * @Version 1.0
 */
public class _02_322_CoinChange2 {

    public int coinChange(int[] coins, int amount) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(coins, amount, path, res);

        if (res.isEmpty()) return -1;

        int min = 0;
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i).size() < res.get(min).size()) {
                min = i;
            }
        }
        return res.get(min).size();
    }

    private void dfs(int[] coins, int amount, List<Integer> path, List<List<Integer>> res) {
        if (amount < 0) return;
        if (amount == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            path.add(coins[i]);
            dfs(coins, amount - coins[i], path, res);
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
