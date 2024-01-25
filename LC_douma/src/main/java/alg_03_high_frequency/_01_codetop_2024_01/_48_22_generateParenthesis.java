package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 11:34
 * @Version 1.0
 */
public class _48_22_generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, "", res, 0, 0);
        return res;
    }

    public void dfs(int n, String path, List<String> res, int open, int close) {

        // 递归边界
        if (path.length() == 2 * n) {
            res.add(new String(path));
        }

        // 剪枝操作
        if (open < n) {
            // 拼接字符串形式，从而避免回溯中还原现场
            dfs(n, path + "(", res, open + 1, close);
        }

        if (close < open) {
            dfs(n, path + ")", res, open, close + 1);
        }
    }
}
