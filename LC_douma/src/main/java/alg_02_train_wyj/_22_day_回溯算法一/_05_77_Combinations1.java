package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 13:28
 * @Version 1.0
 */
public class _05_77_Combinations1 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) return res;
        dfs(n, k, 1, combination, res);
        return res;
    }

    private void dfs(int n, int k, int start, List<Integer> combination, List<List<Integer>> res) {
        if (combination.size() == k) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.add(i);
            dfs(n, k, i + 1, combination, res);
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _05_77_Combinations1().combine(4, 2));
    }
}
