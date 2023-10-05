package alg_02_train_wyj._26_day_动态规划一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 10:24
 * @Version 1.0
 */
public class _09_131_PalindromeSubstring {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(String s, int index, List<String> path, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
        }
        for (int i = index; i < s.length(); i++) {
            String subStr = s.substring(index, i + 1);
            if (!help(subStr)) continue;
            path.add(subStr);
            dfs(s, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    public boolean help(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public List<List<String>> partition1(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

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

        dfs1(s, 0, new ArrayList<>(), res, dp);
        return res;
    }

    public void dfs1(String s, int index,
                     List<String> path,
                     List<List<String>> res,
                     boolean[][] dp) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (!dp[index][i]) continue;
            path.add(s.substring(index, i + 1));
            dfs1(s, i + 1, path, res, dp);
            path.remove(path.size() - 1);
        }
    }
}
