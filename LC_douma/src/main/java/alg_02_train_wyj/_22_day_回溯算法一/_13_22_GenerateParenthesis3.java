package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 21:23
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

        if (open < n) {
            dfs(n, path + "(", res, open + 1, close);
        }
        if (close < open) {
            dfs(n, path + ")", res, open, close + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis3().generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }
}
