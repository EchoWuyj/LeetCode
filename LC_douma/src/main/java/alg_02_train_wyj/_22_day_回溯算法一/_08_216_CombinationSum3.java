package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 16:08
 * @Version 1.0
 */
public class _08_216_CombinationSum3 {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        dfs(k, 1, n, comb, res);
        return res;
    }

    public static void dfs(int k, int start, int n, List<Integer> comb, List<List<Integer>> res) {
//        if (comb.size() > k || n < 0) return;
//        if (comb.size() == k && n == 0)
        if (k < 0 || n < 0) return;
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            dfs(k - 1, i + 1, n - i, comb, res);
            comb.remove(comb.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }
}
