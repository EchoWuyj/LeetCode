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
public class _01_139_word_break1 {

    public boolean wordBreak1(String s, List<String> wordDict) {
        return dfs(s, 0,
                new HashSet<>(wordDict), new HashMap<>());
    }

    public boolean dfs(String s, int index,
                       HashSet<String> set, HashMap<Integer, Boolean> memo) {

        if (index == s.length()) return true;
        if (memo.containsKey(index)) return memo.get(index);

        for (int end = index + 1; end <= s.length(); end++) {
            if (!set.contains(s.substring(index, end))) continue;
            memo.put(index, true);
            if (dfs(s, end, set, memo)) {
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
}
