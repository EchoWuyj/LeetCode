package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 20:39
 * @Version 1.0
 */
public class _13_22_GenerateParenthesis2 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, "", res, 0, 0);
        return res;
    }

    // 使用 open 和 close 来记录 ( 和 ) 个数，通过这个两个形参进行剪枝
    private void dfs(int n, String path, List<String> res, int open, int close) {

        // 剪枝 2 条件
        // 1.二叉树中节点，开括号个数超过一半 => open > n
        //  如：((()
        // 2.二叉树中节点，闭括号个数 > 开括号个数 => close > open
        //  如：())
        // 注意：这里剪枝没有 for 循环，无法使用 continue，而是直接使用 return
        if (open > n || close > open) return;

        // 先剪枝，后加入 path
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }

        // 二叉树 先序 DFS
        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis2().generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }
}
