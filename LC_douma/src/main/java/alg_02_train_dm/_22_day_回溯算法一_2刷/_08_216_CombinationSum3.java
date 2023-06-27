package alg_02_train_dm._22_day_回溯算法一_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 15:20
 * @Version 1.0
 */
public class _08_216_CombinationSum3 {

    /*
        216. 组合总和 III
        找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
        只使用数字 1 到 9
        每个数字  最多使用一次  
        返回 所有可能的有效组合的列表。
        该列表不能包含相同的组合两次，组合可以以任何顺序返回

        示例 1:
        输入: k = 3, n = 7
        输出: [[1,2,4]]
        解释:
        1 + 2 + 4 = 7
        没有其他符合的组合了。

        示例 2:
        输入: k = 3, n = 9
        输出: [[1,2,6], [1,3,5], [2,3,4]]
        解释:
        1 + 2 + 6 = 9
        1 + 3 + 5 = 9
        2 + 3 + 4 = 9
        没有其他符合的组合了。

        示例 3:
        输入: k = 4, n = 1
        输出: []
        解释: 不存在有效的组合。
        在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。


        提示:
        2 <= k <= 9
        1 <= n <= 60

     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        dfs(k, n, 1, combination, res);
        return res;
    }

    private void dfs(int k, int n, int start, List<Integer> comb, List<List<Integer>> res) {
        // 递归边界
        // 因为 dfs 中 k 没有变化，k 还是原来的 k，故使用 comb.size() 和 k 比较
        if (n < 0 || comb.size() > k) return;
        if (n == 0 && comb.size() == k) {
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i <= 9; i++) {
            comb.add(i);
            // k 不变
            dfs(k, n - i, i + 1, comb, res);
            comb.remove(comb.size() - 1);
        }
    }

    public static void dfs1(int k, int start, int n, List<Integer> comb, List<List<Integer>> res) {
        // 递归边界
        // 因为 dfs 中 k 变化了，k-1， k 最后已经减成 0 了，故不能使用 comb.size() 和 k 比较
        if (k < 0 || n < 0) return;
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            // k-1
            dfs1(k - 1, i + 1, n - i, comb, res);
            comb.remove(comb.size() - 1);
        }
    }

    // 总结：
    // 递归边界找 bug，关键看递归参数，列出递归参数变化值
    // 初始值 => k = 3，start = 1，n = 7
    // dfs 1 => k = 2，start = 2，n = 6
    // dfs 2 => k = 1，start = 3，n = 4
    // dfs 3 => k = 0，start = 4，n = 0
}
