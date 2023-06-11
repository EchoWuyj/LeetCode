package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 20:40
 * @Version 1.0
 */
public class _13_22_GenerateParenthesis3 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, "", res, 0, 0);
        return res;
    }

    private void dfs(int n, String path, List<String> res, int open, int close) {
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }
        // 通过 open 和 close 判断是往左遍历，还是往右遍历
        // open < n => 往左遍历 => path + "("
        if (open < n) {
            dfs(n, path + "(", res, open + 1, close);
        }
        // open < open => 往右遍历 => path + ")"
        if (close < open) {
            dfs(n, path + ")", res, open, close + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis3().generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }
}
