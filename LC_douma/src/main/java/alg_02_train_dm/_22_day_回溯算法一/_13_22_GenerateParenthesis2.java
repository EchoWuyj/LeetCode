package alg_02_train_dm._22_day_回溯算法一;

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

    // 记录 open 和 close 个数，通过这个两个形参进行剪枝
    private void dfs(int n, String path, List<String> res, int open, int close) {
        // 剪枝 2 条件
        // 1 open > n
        // 2 close > open
        if (open > n || close > open) return;

        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }

        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis2().generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }
}
