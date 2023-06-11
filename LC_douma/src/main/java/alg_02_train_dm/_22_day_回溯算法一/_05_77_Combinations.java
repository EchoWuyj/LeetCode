package alg_02_train_dm._22_day_回溯算法一;

import alg_02_train_wyj._22_day_回溯算法一._05_77_Combinations1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 13:10
 * @Version 1.0
 */
// 77. 组合
public class _05_77_Combinations {

    /*
        给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
        你可以按 任何顺序 返回答案。

        输入：n = 4, k = 2
        输出：
        [
          [1,2],
          [1,3],
          [1,4],
          [2,4],
          [3,4],
          [2,3],
        ]

        注意
        [1,1] 不算组合，1 被重复使用
        [1,3] 和 [3,1] 算作重复组合，即顺序不同，但是数值相同，这种组合不要

     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) return res;
        dfs(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    // dfs 中通过 start 形参，来控制遍历过程，只能是升序遍历
    // 其中 start 表示子节点从那个值开始的
    private void dfs(int n, int k,
                     int start,
                     List<Integer> combination,
                     List<List<Integer>> res) {
        if (combination.size() == k) {
            res.add(new ArrayList<>(combination));
            return;
        }
        // n 限制数字取值范围
        // KeyPoint 循环变量是 i，而不是 start，start 只是限制 i 的起始值
        for (int i = start; i <= n; i++) {
            // 根据不能重复使用数字的条件，剪枝
            // 因为 i + 1 保证是严格升序的，不存在相等的情况
            // if (combination.contains(i)) continue;
            combination.add(i);
            // KeyPoint 关键技巧点，后面多次使用到
            // i + 1，控制 父节点 < 子节点，从而控制顺序
            // 父节点 i，子节点 i + 1
            dfs(n, k, i + 1, combination, res);
            // 回溯的过程中，将当前的节点从 combination 中删除
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _05_77_Combinations1().combine(4, 2));
        // [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
    }
}
