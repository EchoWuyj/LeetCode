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
        dfs(n, "", 0, 0, res);
        return res;
    }

    public void dfs(int n, String path, int open, int close, List<String> res) {

        if (open > n || close > open) return;
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }
        dfs(n, path + '(', open + 1, close, res);
        dfs(n, path + ")", open, close + 1, res);
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis2().generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }
}
