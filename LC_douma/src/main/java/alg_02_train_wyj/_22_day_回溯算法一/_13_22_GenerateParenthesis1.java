package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 21:03
 * @Version 1.0
 */
public class _13_22_GenerateParenthesis1 {

    private List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, "", res);
        return res;
    }

    public void dfs(int n, String path, List<String> res) {
        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }
        dfs(n, path + '(', res);
        dfs(n, path + ')', res);
    }

    public static void main(String[] args) {
        System.out.println(new _13_22_GenerateParenthesis1().generateParenthesis(2));
        // [((((, (((), (()(, (()), ()((, ()(), ())(, ())),)(((, )((), )()(, )()), ))((, ))(), )))(, ))))]
    }
}
