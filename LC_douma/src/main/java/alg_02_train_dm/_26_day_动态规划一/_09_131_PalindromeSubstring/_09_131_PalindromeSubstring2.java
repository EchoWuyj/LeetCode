package alg_02_train_dm._26_day_动态规划一._09_131_PalindromeSubstring;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 11:53
 * @Version 1.0
 */
public class _09_131_PalindromeSubstring2 {

    // 回溯 + 动态规划
    // 1.回溯 => 获取所有路径
    // 2.动态规划 => 避免重复计算
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        // KeyPoint 动态规划 => 计算字符串的回文子串，只需要计算一次，形成二维 dp 表，后续直接使用
        // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // 状态初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // 一个字符，肯定是回文
        }
        // 状态转移
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 只有两个字符
                if (j - i == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                    // 大于两个字符
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
            }
        }

        dfs(s, 0, new ArrayList<>(), res, dp);
        return res;
    }

    private void dfs(String s, int start,
                     List<String> path,
                     List<List<String>> res,
                     boolean[][] dp) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // [start, i]，不是回文，则剪枝
            // => 直接通过区间 [start,i]，而不是使用 subStr
            if (!dp[start][i]) continue;
            path.add(s.substring(start, i + 1));
            dfs(s, i + 1, path, res, dp);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<String>> res
                = new _09_131_PalindromeSubstring2().partition("aab");
        for (List<String> path : res) {
            System.out.println(path);
        }
    }
}
