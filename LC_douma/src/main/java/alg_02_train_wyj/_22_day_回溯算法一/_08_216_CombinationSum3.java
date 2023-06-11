package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 16:08
 * @Version 1.0
 */
public class _08_216_CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        dfs(k, n, 1, combination, res);
        return res;
    }

    private void dfs(int k, int n, int start, List<Integer> combination, List<List<Integer>> res) {
        if (n < 0 || combination.size() > k) return;
        if (n == 0 && combination.size() == k) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= 9; i++) {
            combination.add(i);
            dfs(k, n - i, i + 1, combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}
