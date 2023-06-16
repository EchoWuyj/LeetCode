package alg_02_train_wyj._29_day_动态规划四;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-06-13 12:20
 * @Version 1.0
 */
public class _01_139_word_break {
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, set, 0, new HashMap<>());
    }

    private boolean dfs(String s, Set<String> set, int index, HashMap<Integer, Boolean> memo) {
        if (index == s.length()) return true;
        if (memo.containsKey(index)) return memo.get(index);

        for (int end = index + 1; end <= s.length(); end++) {
            if (!set.contains(s.substring(index, end))) continue;
            memo.put(index, true);
            if (dfs(s, set, end, memo)) {
                return true;
            }
        }
        memo.put(index, false);
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                if (!set.contains(s.substring(i, j))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs1(s, set, s.length(), new HashMap<>());
    }

    public boolean dfs1(String s, Set<String> set, int index, HashMap<Integer, Boolean> memo) {
        if (index == 0) return true;
        if (memo.containsKey(index)) return memo.get(index);
        for (int start = index - 1; start >= 0; start--) {
            if (!set.contains(s.substring(start, index))) continue;
            memo.put(index, true);
            if (dfs1(s, set, start, memo)) {
                return true;
            }
        }
        memo.put(index, false);
        return false;
    }

    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (!set.contains(s.substring(j, i))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
