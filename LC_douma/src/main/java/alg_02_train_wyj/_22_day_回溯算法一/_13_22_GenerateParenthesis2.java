package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 21:15
 * @Version 1.0
 */
public class _13_22_GenerateParenthesis2 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, "", res, 0, 0);
        return res;
    }

    public void dfs(int n, String path, List<String> res, int open, int close) {
        if (open > n || close > open) return;
        if (path.length() == 2 * n) {
            res.add(new String(path));
        }

        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis2().generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }
}
