package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 20:39
 * @Version 1.0
 */
public class _13_22_GenerateParenthesis1 {
    /*
        22. 括号生成
        数字 n 代表生成括号的对数，请你设计一个函数，
        用于能够生成所有可能的并且 有效的 括号组合。

        KeyPoint 先将问题抽象成树形问题 => 先不考虑剪枝，列出所有可能，之后再去剪枝

        提示：1 <= n <= 8

        O(n^3) => 200 ~ 500
        O(2^n) => 20 ~ 24
        O(n!) => 12
     */

    // n 表示有效括号的对数
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, "", res);
        return res;
    }

    // 精确时间复杂度 O(2^2n)，简化为 O(2^n) 指数级别时间复杂度
    // KeyPoint 回溯(dfs) 时间复杂度分析，关键看递归调用次数，从而决定时间复杂度
    private void dfs(int n, String path, List<String> res) {
        // 2 对括号，故 path 长度是 4，为递归边界
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }

        // 因为每层只有 "(" 和 ")"，所以不使用 for 循环进行遍历，直接使用前后两次递归，类似于二叉树的前序遍历
        dfs(n, path + "(", res);
        dfs(n, path + ")", res);
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis1().generateParenthesis(2));
        // 打印输出所有的组合，其中有不满足题目要求的情况，后续进行剪枝
        // [((((, (((), (()(, (()), ()((, ()(), ())(, ())), )(((, )((), )()(, )()), ))((, ))(), )))(, ))))]
    }
}
