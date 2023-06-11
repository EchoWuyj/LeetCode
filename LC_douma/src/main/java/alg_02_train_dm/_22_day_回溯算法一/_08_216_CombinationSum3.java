package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 15:20
 * @Version 1.0
 */
public class _08_216_CombinationSum3 {

    /*
        找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
        只使用数字 1 到 9
        每个数字  最多使用一次  
        返回 所有可能的有效组合的列表。该列表不能包含相同的组合两次，组合可以以任何顺序返回

     */
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
